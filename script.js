document.addEventListener('DOMContentLoaded', function() {
    
    // 1. Guardia de Seguridad 💂‍♂️ (Solo para el Dashboard)
    const tableBody = document.getElementById('lista-citas');
    if (tableBody) { 
        if (sessionStorage.getItem('adminLogueado') !== 'true') {
            alert("Acceso denegado. Por favor, inicia sesión.");
            window.location.href = "login.html";
            return; 
        }
    }

    // 2. Lógica del Formulario (index.html) 📝 - ¡ESTO ES LO QUE FALTABA!
    const appointmentForm = document.getElementById('appointment-form');
    if (appointmentForm) {
        const feedbackDiv = document.getElementById('form-feedback');
        
        appointmentForm.addEventListener('submit', function(event) {
            event.preventDefault(); 

            // Capturamos los datos del formulario
            const appointmentData = {
                fullName: document.getElementById('fullName').value.trim(),
                email: document.getElementById('email').value.trim(),
                phone: document.getElementById('phone').value.trim(),
                service: document.getElementById('serviceType').value,
                date: document.getElementById('appointmentDate').value,
                message: document.getElementById('message').value.trim()
            };

            // Guardamos en localStorage
            const appointments = JSON.parse(localStorage.getItem('citas')) || [];
            appointments.push(appointmentData);
            localStorage.setItem('citas', JSON.stringify(appointments));

            // Feedback visual
            if (feedbackDiv) {
                feedbackDiv.textContent = `¡Gracias, ${appointmentData.fullName}! Cita registrada con éxito.`;
                feedbackDiv.classList.remove('hidden');
                feedbackDiv.style.color = "green";
                setTimeout(() => feedbackDiv.classList.add('hidden'), 5000);
            }
            
            appointmentForm.reset();
        });
    }

    // 3. Lógica de la Tabla (dashboard.html) 📊
    function renderAppointments() {
        const tableBody = document.getElementById('lista-citas');
        if (!tableBody) return; 

        const appointments = JSON.parse(localStorage.getItem('citas')) || [];
        tableBody.innerHTML = '';

        appointments.forEach(function(appointment, index) {
            const row = document.createElement('tr');
            // Añadimos la celda de "message" para que coincida con tu HTML
            row.innerHTML = `
                <td><strong>${appointment.fullName}</strong></td>
                <td>${appointment.email}</td>
                <td>${appointment.service}</td>
                <td>${appointment.date}</td>
                <td>${appointment.phone}</td>
                <td>${appointment.message || 'Sin mensaje'}</td>
                <td><button class="btn btn-danger btn-sm" onclick="deleteAppointment(${index})">Eliminar</button></td>
            `;
            tableBody.appendChild(row);
        });
    }

    renderAppointments();

    // 4. Cerrar Sesión 🚪
    const logoutBtn = document.getElementById('btn-logout');
    if (logoutBtn) {
        logoutBtn.addEventListener('click', function() {
            sessionStorage.removeItem('adminLogueado');
            window.location.href = "index.html";
        });
    }

    // 5. Función global para eliminar 🗑️
    window.deleteAppointment = function(index) {
        if(confirm("¿Seguro que deseas eliminar esta cita?")) {
            let appointments = JSON.parse(localStorage.getItem('citas')) || [];
            appointments.splice(index, 1);
            localStorage.setItem('citas', JSON.stringify(appointments));
            renderAppointments();
        }
    };
});