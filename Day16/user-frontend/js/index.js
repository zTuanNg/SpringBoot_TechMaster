const URL_API = "http://localhost:8080";
const tableEl = document.querySelector("tbody");
const searchKeyword = document.querySelector("#search");

let users = [];


// ===============API=============== 

// Get data api
const getUserAPI = () => {
    return axios.get(`${URL_API}/api/v1/users`);
}

// Delete user api
const deleteUserApi = (id) => {
    return axios.delete(`${URL_API}/api/v1/users/${id}`);
}

// Find User by name api
const findByNameApi = (userName) =>{
    return axios.get(`${URL_API}/api/v1/users/search?name=${userName}`)
}


// ------------------------------------


// ===============LOGIC=============== 

// 1. Get User function
const getUsers = async () => {
    try {
        let res = await getUserAPI();
        users = res.data;
        // renderUsers(res.data);
        renderPaginationAndUser(users);
    } catch (error) {
        console.log(error);
    }
}

// 2. Delete User function

const deleteUser = async (id) => {
    try {

        let isConfirm = confirm(`Delete User with id = ${id} ?`);
        if (isConfirm) {
            await deleteUserApi(id);
            users = users.filter(user => user.id != id);
            renderPaginationAndUser(users);
        }

    } catch (error) {
        console.log(error);
    }
}

// 3. Find by name function 
const findByName = async () =>{

    let keyword = searchKeyword.value;

    try{
        let res = await findByNameApi(keyword);
        renderPaginationAndUser(res.data);
    }catch(error){
        console.log(error);
    }


}



// Render
const renderUsers = (users, pagination) => {

    tableEl.innerHTML = "";
    let html = "";

    if(users.lenght == 0){
        tableEl.innerHTML = " Khong tim duoc ket qua phu hop..."
    }

    users.forEach((user, index) => {
        html += `
        <tr>
        <td>${pagination.pageSize * (pagination.pageNumber - 1) + (index + 1)}</td>
        <td>${user.name}</td>
        <td>${user.email}</td>
        <td>${user.phone}</td>
        <td>${user.address}</td>
        <td>
            <a href="./detail.html?id=${user.id}" 
            class="btn btn-success">Xem chi tiết
            </a>
            <button class="btn btn-danger" onclick="deleteUser(${user.id})">Xóa</button>
        </td>
    </tr>
        `
    });

    tableEl.innerHTML = html;

}


// Pagination
const renderPaginationAndUser = (arr) => {
    $(".pagination-container").pagination({
        dataSource: arr,
        pageSize: 5,
        // showPrevious : false,
        // showNext : false,
        callback: function (data, pagination) {
            console.log(`data: ${data}`);
            console.log(`pagination: ${pagination}`);
            renderUsers(data, pagination);
        },
    });
};


searchKeyword.addEventListener("keypress", function(event){
    if(event.key === "Enter"){
        findByName();
    }
})

// ===============RUN=============== 

getUsers();

// renderPaginationAndUser(users)