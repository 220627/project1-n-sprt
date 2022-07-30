
const url = "http://localhost:3000"

document.getElementById("welcome-first-name").innerText = "Welcome, " + localStorage.getItem("username");

document.getElementById("approved").onclick=getApproved;
document.getElementById("pending").onclick=getPending;
//document.getElementById("denied").onclick=getDenied;
//document.getElementById("submit-new").onclick=insertNew;

async function getPending() {

    let response = await fetch(url + "/reimbursement/status/2")

    if (response.status === 200) {
        let data= await response.text()

        parsed = JSON.parse(data);

        console.log(parsed);
        for (let reimb of parsed) {
            if (localStorage.getItem("roleId") == 2){
                if (reimb.reimbAuthor["ersUsername"] == localStorage.getItem("username")){
                    
                    let row = document.createElement("tr")
                    let amtCell = document.createElement("td");
                    amtCell.innerHTML = reimb.reimbAmt;
                    let submittedCell = document.createElement("td");
                    submittedCell.innerText = reimb.reimbSubmitted;
                    let resolvedTBA = document.createElement("td");
                    resolvedTBA.innerText = "--";
                    let receiptTBA = document.createElement("td");
                    receiptTBA.innerText = "--";
                    //let resolvedCell = document.createElement("td");
                    //resolvedCell.innerText = reimb.reimbResolved;
                    //let receiptCell = document.createElement("td");
                    //receiptCell.innerText = reimb.reimbReceipt;
                    let authorCell = document.createElement("td");
                    authorCell.innerText = reimb.reimbAuthor["ersUsername"];
                    let resolverTBA = document.createElement("td");
                    resolverTBA.innerHTML = "--";
                    //let resolverCell = document.createElement("td");
                    //resolverCell.innerText = reimb.reimbResolverFk;
                    //let statusCell = document.createElement("td");
                    //statusCell.innerText = reimb.reimbStatusIdFk;
                    let typeCell = document.createElement("td");
                    typeCell.innerText = reimb.reimbType;
                    let statusCell = document.createElement("td");
                    statusCell.innerText = "Pending";
        
                    row.appendChild(amtCell);
                    row.appendChild(submittedCell);
                    row.appendChild(resolvedTBA);
                    row.appendChild(receiptTBA);
                    row.appendChild(authorCell);
                    row.appendChild(resolverTBA);
                    row.appendChild(typeCell);
                    row.appendChild(statusCell);
        
                    document.getElementById("specialist-chosen-info").appendChild(row);
                    
                }
            } else if (localStorage.getItem("roleId") == 1) {
                    
                    let recordId = reimb.reimbId;
                    let row = document.createElement("tr")
                    let amtCell = document.createElement("td");
                    amtCell.innerHTML = reimb.reimbAmt;
                    let submittedCell = document.createElement("td");
                    submittedCell.innerText = reimb.reimbSubmitted;
                    let resolvedTBA = document.createElement("td");
                    resolvedTBA.innerText = "--";
                    let receiptTBA = document.createElement("td");
                    receiptTBA.innerText = "--";
                    //let resolvedCell = document.createElement("td");
                    //resolvedCell.innerText = reimb.reimbResolved;
                    //let receiptCell = document.createElement("td");
                    //receiptCell.innerText = reimb.reimbReceipt;
                    let authorCell = document.createElement("td");
                    authorCell.innerText = reimb.reimbAuthor["ersUsername"];
                    let resolverTBA = document.createElement("td");
                    //resolverTBA.innerHTML = "<button id='resolve-button' onClick=nav(reimb.recordId)>Resolve</button>";
                    //let resolverCell = document.createElement("td");
                    //resolverCell.innerText = reimb.reimbResolverFk;
                    //let statusCell = document.createElement("td");
                    //statusCell.innerText = reimb.reimbStatusIdFk;
                    let typeCell = document.createElement("td");
                    typeCell.innerText = reimb.reimbType;
                    let statusCell = document.createElement("td");
                    statusCell.innerText = "Pending";
                    let approveButton = document.createElement("button");
                    approveButton.setAttribute("id", "approve-button");
                    approveButton.innerText = "Approve";
                    console.log("record id: " + recordId)
                    approveButton.onclick = function () {
                        finalizeRecord(recordId);
                    }

                    
        
                    row.appendChild(amtCell);
                    row.appendChild(submittedCell);
                    row.appendChild(resolvedTBA);
                    row.appendChild(receiptTBA);
                    row.appendChild(authorCell);
                    row.appendChild(resolverTBA);
                    row.appendChild(typeCell);
                    row.appendChild(statusCell);
                    row.appendChild(approveButton);
        
                    document.getElementById("specialist-chosen-info").appendChild(row);
                }
            }
            
    }

}

function currentUser() {
    return localStorage.getItem("userId")
}

function nav(id) {
    location.href="../reimbursement/creation/NewReimbursement.html"
    localStorage.setItem("recordId", id)
    console.log("id: " + id);
}

async function finalizeRecord(reimb) {
    let body = {
        "reimbId": reimb,
        "resolverId": localStorage.getItem("userId"),
        "statusId": 1
        
    }
    let response = await fetch(url + "/reimbursement/finalize", {
        method: "PATCH",
        body: JSON.stringify(body)
    })    

    if (response.status === 202) {
        console.log("successful");
    }
}   

async function getApproved() {
        console.log("welcome to getApproved");
        let response = await fetch(url + "/reimbursement/status/1");
    console.log(response);
        if (response.status === 200) {
            let data = await response.text();
            console.log("data" + data)

            let whatWeWorkWith = JSON.parse(data);
            console.log("what we workin wit: " + whatWeWorkWith);

            for (let reimb of data) {

            
                console.log(reimb);
                let row = document.createElement("tr")
                let amtCell = document.createElement("td");
                amtCell.innerText = reimb.reimb_amount;
                let submittedCell = document.createElement("td");
                submittedCell.innerText.reimb_submitted;
    
                row.appendChild(amtCell);
                row.appendChild(submittedCell);
    
                document.getElementById("specialist-chosen-info").appendChild(row);

            }
        }
        
}


async function updateRecord() {

}
