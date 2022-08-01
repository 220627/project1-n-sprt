
const url = "http://localhost:3000"

document.getElementById("welcome-first-name").innerText = "Welcome, " + localStorage.getItem("username");

document.getElementById("all").onclick = allRecords;
document.getElementById("pending").onclick = allPendingRecords;

let userId = localStorage.getItem("userId");

function emptyTbody() {
    if (document.getElementById("specialist-chosen-info").hasChildNodes) {
        let tbody = document.getElementById("specialist-chosen-info")

        while (tbody.firstChild) {
            tbody.removeChild(tbody.firstChild);
        }
    }
}


async function allRecords() {

    let response = await fetch(url + "/reimbursement/user/all/" + userId)

    if (response.status === 200) {
        
        let data= await response.text()

        parsed = JSON.parse(data);
        
        emptyTbody();

        

        for (let reimb of parsed) {
                    
            let recordId = reimb.reimbId;
            let row = document.createElement("tr");
            row.setAttribute("class", "init");
            let amtCell = document.createElement("td");
            amtCell.innerHTML = reimb.reimbAmt;
            let submittedCell = document.createElement("td");
            submittedCell.innerText = reimb.reimbSubmitted;


            //let resolvedTBA = document.createElement("td");
            //resolvedTBA.innerText = "--";
            //let receiptTBA = document.createElement("td");
            //receiptTBA.innerText = "--";

            let resolvedCell = document.createElement("td");

            if (reimb.reimbResolved != undefined) {
                resolvedCell.innerText = reimb.reimbResolved;
            } else {
                resolvedCell.innerText = "--";
            }
            
            let receiptCell = document.createElement("td");

            if (reimb.reimbReceipt){
                receiptCell.innerHTML = "<a href='view-receiptCell.html'>View receipt</a>";
            }
            
            
            let authorCell = document.createElement("td");
            authorCell.innerText = reimb.reimbAuthor["ersUsername"];
            //let resolverTBA = document.createElement("td");
            //resolverTBA.innerHTML = "<button id='resolve-button' onClick=nav(reimb.recordId)>Resolve</button>";
            let resolverCell = document.createElement("td");
            
            if (reimb.reimbResolver != null) {
                resolverCell.innerText = reimb.reimbResolver["ersUsername"]
            }
            else {
                resolverCell.innerText = "--"
            }   
            //resolverCell.innerText = reimb.reimbResolverFk;
            //let statusCell = document.createElement("td");
            //statusCell.innerText = reimb.reimbStatusIdFk;
            let typeCell = document.createElement("td");
            typeCell.innerText = reimb.reimbType;
            let statusCell = document.createElement("td");
            
            if (reimb.reimbStatus != undefined){
                statusCell.innerText = reimb.reimbStatus["reimbStatus"];
            } else {
                statusCell.innerText = "--"
            }
            
                    
        
                    row.appendChild(amtCell);
                    row.appendChild(submittedCell);
                    row.appendChild(resolvedCell);
                    row.appendChild(receiptCell);
                    row.appendChild(authorCell);
                    row.appendChild(resolverCell);
                    row.appendChild(typeCell);
                    row.appendChild(statusCell);

                    document.getElementById("specialist-chosen-info").appendChild(row);
                    
                }
            }



}


async function allPendingRecords() {

    let response = await fetch(url + "/reimbursement/user/pending/" + userId)

    if (response.status === 200) {
        
        let data= await response.text()

        parsed = JSON.parse(data);
        
        emptyTbody();

        

        for (let reimb of parsed) {
                    
                    let row = document.createElement("tr");
                    row.setAttribute("class", "init");
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
            }



}