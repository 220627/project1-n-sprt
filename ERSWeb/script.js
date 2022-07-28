const url = "http://localhost:3000"

document.getElementById("enter-button").onclick=login;


async function login () {
    console.log("running login")
    let user = document.getElementById("login-username").value
    let pass = document.getElementById("login-password").value

    let userCreds = {
        username: user,
        password: pass
    }

    

    let response = await fetch(url + "/users/login", {
        method: "POST",
        body: JSON.stringify(userCreds),
        credentials: "include"
    })
    
    
     
    if (response.status === 202) {
        let data = await response.text();

        const userSession = JSON.parse(data);
        
        console.log(userSession.userId);
        localStorage.setItem("userId", userSession.userId);
        localStorage.setItem("username", userSession.userUsername);
        localStorage.setItem("roleId", userSession.roleId);


        roleId = userSession.roleId



        if (roleId != 0){
            if (roleId == 1) {
                window.location.href = "users/manager.html";
            } else if (roleId == 2) {
                window.location.href = "users/specialist.html";
            }
        } else {
            document.getElementById("login-form-message-center").innerText("Could not recognize user");
        } 
        
        

    } else {
        
        console.log("Login unsuccessful")
    }

}
