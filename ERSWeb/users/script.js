
const url = "http://localhost:3000"

document.getElementById("welcome-first-name").innerText = "Welcome, " + localStorage.getItem("username");

document.getElementById("approved").onclick=getApproved;
document.getElementById("pending").onclick=getPending;
document.getElementById("denied").onclick=getDenied;
document.getElementById("submit-new").onclick=insertNew;

funtion getApproved(){

    let response = await fetch(url + "/reimbursement/status/1");

    if (response.status === 200) {
        let data = await response.json();
        renderHTML(data);
    }


    )

}