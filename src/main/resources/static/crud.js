let mainUsersUrl = "/api/users"
let result
let listOfUsers = document.querySelector('#getAllUsers')
const editButton = document.getElementById('editButton')
const deleteButton = document.getElementById('deleteButton')
// document.addEventListener('DOMContentLoaded', function() {
//     let modalButtons = document.querySelectorAll('js-open-modal'),
//         overlay = document.querySelector('#overlay-modal'),
//         closeButtons = document.querySelectorAll('js-modal-close')
//     modalButtons.forEach(function(item) {
//         item.addEventListener('click', function(event) {
//             event.preventDefault()
//             let modalId = this.getAttribute('data-modal'),
//                 modalelem = document.querySelector('.modal[data-modal ="'+ modalId +'"]');
//             modalElem.classList.add('active')
//             overlay.classList.add('active')
//         });
//     })
// })

$(async function() {
    await getAllUsers()
})

async function getAllUsers() {
    fetch(mainUsersUrl)
        .then(response => response.json())
        .then((users) => {
            users.forEach((user) => {
                result += `
                       <tr>
                           <td>${user.id}</td>
                           <td>${user.name}</td>
                           <td>${user.surName}</td>
                           <td>${user.age}</td>
                           <td>${user.email}</td>
                           <td>${user.roles.map(role => role.name)}</td>
                           <td> 
                               <button type="button"
                                    id = "editButton"
                                    class="btn btn-primary btn-sm text-white text-decoration-none text-center btn-info js-open-modal"
                                    data-toggle="modal"
                                    data-target="#edit"
                                    data-userid="${user.id}">Edit
                               </button>
                           </td>
                           <td>
                               <button type="button"
                                  id = "deleteButton"
                                  class="btn btn-primary btn-sm text-white text-decoration-none text-center btn-danger js-open-modal"
                                  data-toggle="modal"
                                  data-target="#delete"
                                  data-userid="${user.id}">Delete
                               </button>   
                           </td>
                       </tr>`
            })

            listOfUsers.innerHTML = result
        })
}

