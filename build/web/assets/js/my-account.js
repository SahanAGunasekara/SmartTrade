//window.onload = function(){
//    console.log("OK");
//}

window.addEventListener("load",async function(){
    //console.log("OK");
    const response = await fetch("MyAccount");
    
    if (response.ok) {
        const json = await response.json();
        
        document.getElementById("username").innerHTML =`Hello, ${json.firstName} ${json.lastName}`;
        document.getElementById("since").innerHTML =`Smart Trade Member Since ${json.since}`;
        document.getElementById("fname").value = json.firstName;
        document.getElementById("lname").value = json.lastName;
        document.getElementById("cpassword").value = json.password;
    }else{
        
        
    }
});

window.addEventListener("load",async function(){
    const response = await fetch("CityData");
    
    if (response.ok) {
        const json = await response.json();
        //console.log(json);
        const citySelect = document.getElementById("citySelect");
        json.forEach(city=>{
            let option = document.createElement("option");
            option.innerHTML = city.name;
            option.value=city.id;
            citySelect.appendChild(option);
        });
        
        
        
    }
});

async function saveChanges(){
    
    const firstName = document.getElementById("fname").value;
    const lastName = document.getElementById("lname").value;
    const lineOne = document.getElementById("lineOne").value;
    const lineTwo = document.getElementById("lineTwo").value;
    const postalCode = document.getElementById("postalCode").value;
    const cityId = document.getElementById("citySelect").value;
    const cpassword = document.getElementById("cpassword").value;
    const npassword = document.getElementById("npassword").value;
    const cnpassword = document.getElementById("cnpassword").value;
    
    const userObject={
        firstName:firstName,
        lastName:lastName,
        lineOne:lineOne,
        lineTwo:lineTwo,
        postalCode:postalCode,
        cityId:cityId,
        currentPassword:cpassword,
        newPassword:npassword,
        confirmPassword:cnpassword
    };
    
    const userDataJson = JSON.stringify(userObject);
    
    
    //console.log("OK");
    const response = await fetch("MyAccount",{
        method:"PUT",
        headers:{
            "Content-Type":"application/json"
        },
        body:userDataJson
    }
    );
    
    if (response.ok) {
        const json = await response.json();
        if(json.status){
            console.log(json.message);
        }else{
            console.log(json.message);
        }
    }
}


