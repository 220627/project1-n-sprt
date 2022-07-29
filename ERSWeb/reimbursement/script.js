document.getElementById("create-new-reimbursement-button").onclick=insertReimbursement;

async function insertReimbursement() {
    let body = { 
        amount: document.getElementById("new-reimbursement-amount").value,
        type: document.getElementById("new-reimbursement-type").value,
        user: localStorage.getItem("userId")

    }

    

console.log(body);

}