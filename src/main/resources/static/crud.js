
// const fetchUsers = async () => {
//     try {
//         const res = await fetch('http://localhost:8080/api/users');
//         if (!res.ok) {
//             throw new Error(res.status);
//         }
//         const data = await res.json();
//         console.log(data);
//     } catch (error) {
//         console.log(error);
//     }
// }

//fetchUsers();

fetch('http://localhost:8080/api/users')
    .then(res => res.json())
    .then(res => {
        res.data.map(user => {
            console.log(`${user.id}: ${user.name} ${user.surName}`);
        });
    });
