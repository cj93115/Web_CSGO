document.querySelectorAll('.running-button').forEach(btn => {
    btn.addEventListener('click', e => {
        btn.classList.toggle('active');
    });
});