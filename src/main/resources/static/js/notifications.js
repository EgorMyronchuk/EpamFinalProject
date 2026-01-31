document.addEventListener('DOMContentLoaded', function() {
    // Ищем все элементы с классом .toast
    const notifications = document.querySelectorAll('.toast');

    notifications.forEach(notification => {
        setTimeout(() => {
            notification.classList.add('fade-out');
            setTimeout(() => notification.remove(), 500);
        }, 4000);
    });
});