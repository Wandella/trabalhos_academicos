function mostraEsconde(id, mostra) {

    if (mostra == 1) {
        document.getElementById(id).style.display = 'block';
    } else {
        document.getElementById(id).style.display = 'none';
    }
}