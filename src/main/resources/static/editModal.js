$('#editModalContainer').on('show.bs.modal', ev => {
    let button = $(ev.relatedTarget);
    let id = button.data('user-id');
    viewEditModal(id);
})

$(async function () {
    await editCurrentUser();
});

async function viewEditModal(id) {
    let userEdit = await getUser(id);
    let formEdit = document.getElementById('formEditUser');
    formEdit.id.value = id;
    formEdit.username.value = userEdit.username;
    formEdit.email.value = userEdit.email;
    formEdit.password.value = userEdit.password;
    $('#roleInput').empty();
    console.log(formEdit.id.value);
    await fetch('http://localhost:8080/api/role')
        .then(r => r.json())
        .then(roles => {
            roles.forEach(role => {
                let selectedRole = false;
                for (let i = 0; i < userEdit.roles.length; i++) {
                    if (userEdit.roles[i].name === role.name) {
                        selectedRole = true;
                        break;
                    }
                }
                let element = document.createElement("option");
                element.text = role.name;
                element.value = role.id;
                if (selectedRole) element.selected = true;
                $('#roleInput')[0].appendChild(element);
            })
        })
        .catch((error) => {
            alert(error);
        })
}

async function getUser(id) {
    let url = 'http://localhost:8080/api/' + id;
    let response = await fetch(url);
    return await response.json();
}

async function editCurrentUser() {
    let formEdit = document.forms["formEditUser"];
    formEdit.addEventListener("submit", async (e)=> {
        e.preventDefault();
        let editUserRoles = [];
        for (let i = 0; i < formEdit.roles.options.length; i++) {
            if (formEdit.roles.options[i].selected) editUserRoles.push({
                id: formEdit.roles.options[i].value,
                name: formEdit.roles.options[i].role,
            });
        }
        await fetch('http://localhost:8080/api/update', {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: formEdit.id.value,
                username: formEdit.username.value,
                email: formEdit.email.value,
                password: formEdit.password.value,
                roles: editUserRoles,
            })
            })
                .then(() => {
                formEdit.reset();
                showUsersTable();
                $('#closeEdit').click();
            })
                .catch((error) => {
                    alert(error);
                })
        })
}


