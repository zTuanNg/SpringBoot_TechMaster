
const btn = document.getElementById('btn');
const image = document.getElementById('image');
const breedList = document.getElementById('breed-list');
const subBreedList = document.getElementById("sub-breed-list")


const getBreedList = async () => {
    try {
        let res = await axios.get("https://dog.ceo/api/breeds/list/all");
        // console.log(res);
        renderBreedList(res.data.message);
    } catch (error) {
        console.log(error);
    }
}


const renderBreedList = obj => {
    let keys = Object.keys(obj);

    let html = "<option hidden>Choice this option</option>";
    keys.forEach(key => {
        html += `<option value=${key}>${key}</option>`; 
    });

    breedList.innerHTML = html;
}

getBreedList()


btn.addEventListener("click", async () => {
    try {
        subBreedList.innerHTML = "";
        let breedListValue = breedList.value;
        let res = await axios.get(`https://dog.ceo/api/breed/${breedListValue}/list`); 
        // console.log(res);

        if (res.data.message.length != 0){
            renderSubBreedList(res.data.message, breedListValue);
        } else {
            let li = document.createElement("li");
            li.innerText = "Không có sub breed";
            subBreedList.appendChild(li);
        }
        
    } catch (error) {
        console.log(error);
    }
})


function renderSubBreedList (obj, value) {
    let keys = Object.values(obj);

    keys.forEach(key => {
        let li = document.createElement("li");
        li.innerText = `${key}`;
        subBreedList.appendChild(li);

        li.addEventListener("click", async () => {
            try {
                let res = await axios.get(`https://dog.ceo/api/breed/${value}/${key}/images/random`);
                document.querySelector(".hidden").style.display = "block";
                image.src = res.data.message;
            } catch (error) {
                console.log(error)
            }
        });
    });
}
