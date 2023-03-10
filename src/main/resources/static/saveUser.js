$(async function () {
    await saveUser();
});


async function saveUser() {
    await fetch('http://localhost:8080/api/role')
        .then(r => r.json())
        .then(roles => {
            roles.forEach(role => {
                let elementSave = document.createElement("option");
                elementSave.text = role.name;
                elementSave.value = role.id;
                $('#roleSave')[0].appendChild(elementSave);
            })
        })

    let formSave = document.forms["saveUserForm"];
    formSave.addEventListener("submit", function (event) {
        event.preventDefault();
        let editUserRoles = [];
        for (let i = 0; i < formSave.roles.options.length; i++) {
            if (formSave.roles.options[i].selected) editUserRoles.push({
                id: formSave.roles.options[i].value,
                name: formSave.roles.options[i].role,
            });
        }
        fetch('http://localhost:8080/api/save', {

            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: 100,
                username: formSave.username.value,
                password: formSave.password.value,
                email: formSave.email.value,
                roles: editUserRoles
            })
        })
            .then(() => {
                formSave.reset();
                showUsersTable();
                $('#nav-home-tab').click();
            })
            .catch((error) => {
                alert(error);
            })
    })
}