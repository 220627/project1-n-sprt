document.getElementById("create-new-button").onclick=insertReimbursement;

const url = "http://localhost:3000";

async function insertReimbursement() {
    let body = { 
        "reimbAmt": document.getElementById("new-reimbursement-amount").value,
        "reimbType": document.getElementById("new-reimbursement-type").value,
        "reimbAuthorFk": localStorage.getItem("userId"),
        "reimbStatusIdFk": 2

    }
    

    console.log(body)
    console.log(JSON.stringify(body));
   
    
    let response = await fetch(url + "/reimbursement/new-reimbursement", {
        method: "POST",
        body: JSON.stringify(body)
    }) 

    if (localStorage.getItem("roleId") == 2) {
        window.location.href="/users/specialist.html"
    } else if (localStorage.getItem("roleId") == 1){
        window.location.href="/users/manager.html"
    }


    

    console.log(response.status);


}

console.log(localStorage.getItem("recordId"));