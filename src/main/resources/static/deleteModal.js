$('#deleteModalContainer').on('show.bs.modal', ev => {
    let button = $(ev.relatedTarget);
    let idDelete = button.data('user-delete-id');
    viewDeleteModal(idDelete);
})

async function viewDeleteModal(idDelete) {
    let userDelete = await getUser(idDelete);
    let formDelete = document.getElementById('formDeleteUser');
    formDelete.id.value = idDelete;
    formDelete.username.value = userDelete.username;
    formDelete.email.value = userDelete.email;
    formDelete.password.value = userDelete.password;
    $('#roleDelete').empty();

    await fetch('http://localhost:8080/api/role')
        .then(r => r.json())
        .then(roles => {
            roles.forEach(role => {
                let selectedRoleDelete = false;
                for (let i = 0; i < userDelete.roles.length; i++) {
                    if (userDelete.roles[i].name === role.name) {
                        selectedRoleDelete = true;
                        break;
                    }
                }
                let element = document.createElement("option");
                element.text = role.name;
                // element.setAttribute('name', role.name);
                element.value = role.id;
                if (selectedRoleDelete) element.selected = true;
                $('#roleDelete')[0].appendChild(element);
            })
        })
        .catch((error) => {
            alert(error);
        })
}
$(async function () {
    deleteUser();
});
async function deleteUser() {
    let formDelete = document.getElementById('formDeleteUser');
    formDelete.addEventListener("submit", async (e) => {
        e.preventDefault();
        const dataDelete = new FormData(e.target);

        let url = 'http://localhost:8080/api/delete/' + dataDelete.get('id');
        fetch(url, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            }
        })
            .then(() => {
                $('#closeDelete').click();
                showUsersTable();
            })
            .catch((error) => {
                alert(error);
            })
    })
}

