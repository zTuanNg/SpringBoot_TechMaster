const URL_API = "https://list-todos-app.herokuapp.com/api/todos";
let todos = [];


const todoListEl = document.querySelector(".todo-list");
const inputEl = document.querySelector("#todo-input");

const addBtn = document.querySelector("#btn-add");
const updateBtn = document.querySelector("#btn-update");
const todo_option_item = document.querySelectorAll(".todo-option-item input");

// ----------------------------------------------------------------
// ***** DANH SACH API

// 1. Get all todos
const getTodoApi = () => {
    return axios.get(URL_API);
}

// 2. Delete todo
const deleteAPI = (id) => {
    return axios.delete(`${URL_API}/${id}`);
}

// 3. Add todo
const addApi = (s) => {
    return axios.post(URL_API, {
        "title": s
    });
}

// 4. Update Todo
const updateApi = (id, title, status) => {
    return axios.put(`${URL_API}/${id}`,{

        "title" : title,
        "status" : status
    });

}


// 5. Filter status
const filterStatusApi = (status) =>{
    switch(status){
        case "all":{
            return axios.get(URL_API);
        }
        case "active":{
            return axios.get(`${URL_API}/?status=true`)
        }
        case "unactive": {
            return axios.get(`${URL_API}/?status=false`);
        }
        default: {
            return axios.get(`${URL_API}/todos`);
        }
    }
}

// ----------------------------------------------------------------
// ***** LOGIC FUNCTION

//1. Get all todos
const getTodo = async () => {
    try {
        let res = await getTodoApi();
        console.log(res);

        todos = res.data

        renderTodo(todos)
    } catch (error) {
        console.log(error);
    }
}

// 2. Delete Todo
const deleteTodo = async (id) => {
    try {
        let isConfirm = confirm("Delete this todo ?");

        if (isConfirm) {
            await deleteAPI(id);  // delete on server

            // Delete todo in todos
            todos = todos.filter(t => t.id != id);

            // Render todos again
            renderTodo(todos);
        }
    } catch (error) {
        console.log(error);
    }
}

// 3. Add todo
const addTodo = async () => {
    try {
        let title = inputEl.value;
        let todo = await addApi(title);
        console.log(todo.data);

        todos.push(todo.data);
        renderTodo(todos);

        inputEl.value = "";

        // console.log(`Test type Todo : ${typeof todo}`);

    } catch (error) {
        console.log(error);
    }
}

// 4. Update todo

// --- 4.1/ Update status
const toggleStatus = async (id) => {

    try {

        let isConfirm = confirm("Change this Todo'status ?")
        if(isConfirm){
            let todo = todos.find(t => t.id == id);
        
        let changedStatus = !todo.status;
        todo.status = changedStatus;


        let updatedTodo = await updateApi(id, todo.title, todo.status);

        // Update todo in todos
        todos.forEach((todo, index) => {
            if (todo.id == id) {
                todo[index] = updatedTodo.data;
            }
        });

        renderTodo(todos);

        console.log(`Updated todo: ${updatedTodo}`);
        }

    } catch (error) {
        console.log(error);
    }
};

// --- 4.2 Update title
const updateTitle = (id) =>{

    let todo = todos.find(t => t.id == id);
    inputEl.value = todo.title;
    console.log(`todo title : ${inputEl.value}`)

    updateBtn.style.display = "inline-block";
    addBtn.style.display = "none";
    
    updateBtn.addEventListener("click", async()=>{

        try{
            if(inputEl.value == ""){
                alert("Title can not empty");
            }

            console.log(`Input text: ${inputEl.value}`);
            todo.title = inputEl.value;

            console.log(`todo title: ${todo.title}`)

            let updatedTodo = await updateApi(id, todo.title, todo.status);

            // todos.forEach((todo, index) => {
            //     if (todo.id == id) {
            //         todo[index] = updatedTodo.data;
            //     }
            // });

            updateBtn.style.display = "none";
            addBtn.style.display = "inline-block";
            
            renderTodo(todos);
        }catch(error){
            console.log(error);
        }
    });
}

// 5. Filter Status

// Get value in checked radio input
const getSelectedOption = () =>{
    for(let i = 0 ; i < todo_option_item.length; i++){
        if(todo_option_item[i].checked){
            return todo_option_item[i].value;
        }
    }
}

const filterStatus = async (status) =>{
    try{
        
        let filterTodos = await filterStatusApi(status);

        todos = filterTodos.data;
        renderTodo(todos);
    }catch(error){
        console.log(error)
    }
}

todo_option_item.forEach(btn =>{
    btn.addEventListener("click", () =>{

        let filterValue = getSelectedOption();
        filterStatus(filterValue)
    });
});




// ----------------------------------------------------------------

const renderTodo = (arr) => {


    
    inputEl.value = "";

    todoListEl.innerHTML = "";

    if (arr.length == 0) {
        todoListEl.innerHTML = " List Todo is empty ";
        return
    }

    let html = "";

    arr.forEach(t => {
        html += `
         <div class="todo-item ${t.status ? "active-todo" : ""}">
            <div class="todo-item-title">
                <input  type="checkbox" 
                        ${t.status ? "checked" : ""} 
                        onclick="toggleStatus(${t.id})"/>
                <p>${t.title}</p>
            </div>
            <div class="option">
                <button class="btn btn-update" onclick="updateTitle(${t.id})" >
                    <img src="./img/pencil.svg" alt="icon" />
                </button>
                <button class="btn btn-delete" onclick="deleteTodo(${t.id})">
                    <img src="./img/remove.svg" alt="icon" />
                </button>
            </div>
        </div>
         `
    });

    todoListEl.innerHTML = html;

}

// ----------------------------------------------------------------

// RUN 
getTodo();


// ADD todo
// addBtn.addEventListener("click", () =>{
//     addTodo();
// })






