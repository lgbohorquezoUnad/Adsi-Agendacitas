document.getElementById('login-form').addEventListener('submit', function(event) {
    // 1. Evitamos que la página se refresque 🛑
    event.preventDefault();

    // 2. Obtenemos los valores de los cuadros de texto ✍️
    const user = document.getElementById('usuario').value;
    const pass = document.getElementById('contraseña').value;
    const errorDiv = document.getElementById('login-error');

    // 3. Verificamos las credenciales 🔑
    if (user === "admin" && pass === "1234") {
        // Guardamos el permiso solo para esta pestaña 🎒
        sessionStorage.setItem('adminLogueado', 'true');
        // Saltamos al panel de control 🏎️
        window.location.href = "dashboard.html";
    } else {
        // Mostramos el error si algo falla ❌
        errorDiv.classList.remove('hidden');

        // Lo ocultamos automáticamente tras 5 segundos ⏱️
        setTimeout(() => {
            errorDiv.classList.add('hidden');
        }, 5000);
    }
});