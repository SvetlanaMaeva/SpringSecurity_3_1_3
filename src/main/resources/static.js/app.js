// const postsList = document.querySelector('.posts__list');
// const getPostBtn = document.querySelector('.posts__get-posts');
//
// getPostBtn.addEventListener('click' , async () => {
//     await getPostsRequest;
// })
//
// function getPostsRequest() {
//     return fetch('http://localhost:8080/users', {
//         headers: {
//             "Content-type": "application/json; charset=UTF-8"
//         }
//     })
//         .then((res) => res.json())
//         .then((posts) => console.log('posts: ', posts))
//
// }
document.write("test");
async function getAllUser() {
    const res = await fetch('http://localhost:8080/users')
    const resUser = await res.json();
    console.log(resUser);
    // resUser.forEach(resUser => listUserToHTML(resUser))
}
window.addEventListener('DOMContentLoaded', getAllUser);

// function listUserToHTML({id, username, email, password, role}) {
//     const userList = document.getElementById('listUserInput');
//
//     userList.insertAdjacentHTML('after-end', `
//
//         <tr id="listUserInput">
//                     <td text="${id}"/>
//                     <td text="${username}"/>
//                     <td text="${email}"/>
//                     <td text="${password}"/>
//                     <td text="${role}"/>
//                 </tr>
//
//         `);
// }
