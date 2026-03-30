param(
    [Parameter(Mandatory = $true)]
    [string]$InputTextPath,

    [Parameter(Mandatory = $true)]
    [string]$OutputPdfPath
)

$lines = Get-Content -Path $InputTextPath -Encoding UTF8
$linesPerPage = 38
$pages = @()

for ($i = 0; $i -lt $lines.Count; $i += $linesPerPage) {
    $end = [Math]::Min($i + $linesPerPage - 1, $lines.Count - 1)
    $pages += ,($lines[$i..$end])
}

function Escape-PdfText {
    param([string]$Text)

    if ($null -eq $Text) {
        return ""
    }

    return $Text.Replace('\', '\\').Replace('(', '\(').Replace(')', '\)')
}

$objects = New-Object System.Collections.Generic.List[string]
$pageRefs = New-Object System.Collections.Generic.List[string]
$fontObjectNumber = 3 + ($pages.Count * 2)

$objects.Add("<< /Type /Catalog /Pages 2 0 R >>")
$objects.Add("")

for ($pageIndex = 0; $pageIndex -lt $pages.Count; $pageIndex++) {
    $pageObjectNumber = 3 + ($pageIndex * 2)
    $contentObjectNumber = 4 + ($pageIndex * 2)
    $pageRefs.Add("$pageObjectNumber 0 R")

    $contentLines = New-Object System.Collections.Generic.List[string]
    $contentLines.Add("BT")
    $contentLines.Add("/F1 11 Tf")
    $contentLines.Add("50 760 Td")
    $contentLines.Add("14 TL")

    foreach ($line in $pages[$pageIndex]) {
        $escapedLine = Escape-PdfText $line
        $contentLines.Add("($escapedLine) Tj")
        $contentLines.Add("T*")
    }

    $contentLines.Add("ET")
    $streamText = ($contentLines -join "`n")
    $streamLength = [System.Text.Encoding]::ASCII.GetByteCount($streamText)

    $objects.Add("<< /Type /Page /Parent 2 0 R /MediaBox [0 0 612 792] /Resources << /Font << /F1 $fontObjectNumber 0 R >> >> /Contents $contentObjectNumber 0 R >>")
    $objects.Add("<< /Length $streamLength >>`nstream`n$streamText`nendstream")
}

$kids = [string]::Join(" ", $pageRefs)
$objects[1] = "<< /Type /Pages /Kids [ $kids ] /Count $($pages.Count) >>"
$objects.Add("<< /Type /Font /Subtype /Type1 /BaseFont /Helvetica >>")

$builder = New-Object System.Text.StringBuilder
[void]$builder.AppendLine("%PDF-1.4")

$offsets = New-Object System.Collections.Generic.List[int]

for ($i = 0; $i -lt $objects.Count; $i++) {
    $offsets.Add([System.Text.Encoding]::ASCII.GetByteCount($builder.ToString()))
    [void]$builder.AppendLine("$($i + 1) 0 obj")
    [void]$builder.AppendLine($objects[$i])
    [void]$builder.AppendLine("endobj")
}

$xrefPosition = [System.Text.Encoding]::ASCII.GetByteCount($builder.ToString())
[void]$builder.AppendLine("xref")
[void]$builder.AppendLine("0 $($objects.Count + 1)")
[void]$builder.AppendLine("0000000000 65535 f ")

foreach ($offset in $offsets) {
    [void]$builder.AppendLine(("{0:0000000000} 00000 n " -f $offset))
}

[void]$builder.AppendLine("trailer")
[void]$builder.AppendLine("<< /Size $($objects.Count + 1) /Root 1 0 R >>")
[void]$builder.AppendLine("startxref")
[void]$builder.AppendLine("$xrefPosition")
[void]$builder.Append("%%EOF")

[System.IO.File]::WriteAllText($OutputPdfPath, $builder.ToString(), [System.Text.Encoding]::ASCII)
