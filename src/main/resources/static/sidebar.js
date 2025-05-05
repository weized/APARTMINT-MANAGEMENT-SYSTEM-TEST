document.addEventListener("DOMContentLoaded", function () {
    const sidebar = document.getElementById('sidebar');
    const content = document.querySelector('.content');
    const toggleButton = document.getElementById('sidebar-toggle-btn'); // optional button ID

    // Define the toggle function
    window.toggleSidebar = function () {
        if (sidebar && content) {
            const isHidden = sidebar.classList.toggle('hidden');
            content.classList.toggle('full', isHidden);
        }
    };

    // Auto-hide sidebar on smaller screens
    if (window.innerWidth < 768) {
        toggleSidebar();
    }

    // Optional: add click event if your button doesn't use inline `onclick`
    if (toggleButton) {
        toggleButton.addEventListener("click", toggleSidebar);
    }
});
