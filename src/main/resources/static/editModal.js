async function getAllUser() {
    const resUser = await fetch('http://localhost:8080/users')
    let res = await resUser.json();
    console.log(res);

    const keys = Object.keys(res);
    console.log(keys)

    await editModal(res);

    // await getId();

// let buttons = document.querySelectorAll('input[data-user-id]');
//
// for(let button of buttons){
//     button.addEventListener('click',(e)=>{
//         showData(e.currentTarget.dataset.userId);
//         console.log(e);
//     });
//
// }


    //
    // var modal = document.getElementById("editModalContainer");
    // var btn = document.getElementById("myBtn");
    //
    // btn.onclick = function() {
    //     modal.style.display = "block";
    // }
    // window.onclick = function(event) {
    //     if (event.target === modal) {
    //         modal.style.display = "none";
    //     }
    // }
}
window.addEventListener('DOMContentLoaded', getAllUser);

    let buttons = document.querySelectorAll('input[data-user-id]');
    console.log(buttons);

    for (let button of buttons) {
        button.addEventListener('click', async (e) => {
            showData(e.currentTarget.dataset.userId)
        });
    }

    function showData(id) {
        console.log(id);
    }

    function findByUserId(arr, id) {
        return arr.find(item => item.id === id);
    }
    //
    // const modalEl = document.getElementById('editModalContainer');
    // modalEl.addEventListener('show.bs.modal', function (event) {
    //     let id = event.relatedTarget;
    //     let id1 = id.getAttribute('data-user-id');
    //     console.log(id1);
    // });



async function editModal(arr) {

    const modalEl = document.getElementById('editModalContainer');

    let user = "";
    modalEl.addEventListener('show.bs.modal', function (event) {
        let id = event.relatedTarget;
        let id1 = id.getAttribute('data-user-id');
        console.log(id1);
        user = findByUserId(arr, id1);
        console.log(user);
    });
}

