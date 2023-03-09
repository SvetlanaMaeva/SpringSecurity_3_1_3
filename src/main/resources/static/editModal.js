$('#editModalContainer').on('show.bs.modal', ev => {
    let button = $(ev.relatedTarget);
    let id = button.data('user-id');
    viewEditModal(id);
})

$(async function () {
    editCurrentUser();
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
    await fetch('http://localhost:8080/users/role')
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
    let url = 'http://localhost:8080/users/' + id;
    let response = await fetch(url);
    return await response.json();
}
function editCurrentUser() {
    let formEdit = document.getElementById('formEditUser');
    formEdit.addEventListener("submit", function (event) {
        event.preventDefault();
        let editUserRoles = [];
        for (let i = 0; i < formEdit.roles.options.length; i++) {
            if (formEdit.roles.options[i].selected) editUserRoles.push({
                id: formEdit.roles.options[i].value,
                role: formEdit.roles.options[i].name
            });
        }

        fetch('http://localhost:8080/users/update', {

            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: formEdit.id.value,
                firstname: formEdit.username.value,
                email: formEdit.email.value,
                password: formEdit.password.value,
                roles: editUserRoles
            })
        }).then(() => {
            formEdit.reset();
            reloadShowUsers();
            $('#editFormCloseButton').click();
        })
            .catch((error) => {
                usersInfo = ''
                alert(error);
            });
    })
}
const reloadShowUsers = () => {
    fetch('http://localhost:8080/users/')
        .then(response => response.json())
        .then(data => {
            listUserToHTML(data)
        })
}