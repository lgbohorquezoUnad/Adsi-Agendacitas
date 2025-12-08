document.addEventListener('DOMContentLoaded', function() {
    
    // Smooth scrolling for navigation links
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', function (e) {
            e.preventDefault();

            document.querySelector(this.getAttribute('href')).scrollIntoView({
                behavior: 'smooth'
            });
        });
    });

    // Handle form submission
    const form = document.getElementById('appointment-form');
    const feedbackDiv = document.getElementById('form-feedback');
    const today = new Date().toISOString().split('T')[0];
    document.getElementById("appointmentDate").setAttribute('min', today);

    form.addEventListener('submit', function(event) {
        // Prevent the default form submission
        event.preventDefault(); 

        // Get form values
        const fullName = document.getElementById('fullName').value.trim();
        const email = document.getElementById('email').value.trim();
        const phone = document.getElementById('phone').value.trim();
        const service = document.getElementById('serviceType').value;
        const date = document.getElementById('appointmentDate').value;

        // Simple validation
        if (!fullName || !email || !phone || !service || !date) {
            showFeedback('Por favor, complete todos los campos requeridos.', 'error');
            return;
        }
        
        // --- Simulation of sending data to a server ---
        // In a real application, you would send this data to your backend here.
        // For example, using the fetch() API.
        
        console.log('Form data submitted:');
        console.log({ fullName, email, phone, service, date });

        // Show a success message
        showFeedback(`¡Gracias, ${fullName}! Hemos recibido su solicitud para el servicio de '${service}' en la fecha ${date}. Nos pondremos en contacto pronto para confirmar.`, 'success');
        
        // Reset the form after submission
        form.reset();
    });
    
    // Helper function to show feedback messages
    function showFeedback(message, type) {
        feedbackDiv.className = ''; // Reset classes
        feedbackDiv.classList.add(type); // 'success' or 'error'
        feedbackDiv.textContent = message;
        feedbackDiv.classList.remove('hidden');

        // Hide the message after 5 seconds
        setTimeout(() => {
            feedbackDiv.classList.add('hidden');
        }, 5000);
    }
});