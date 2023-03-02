// Get references to DOM elements
var addProjectBtn = document.getElementById("add-project-btn");
var overlay = document.getElementById("add-project-overlay");
var closeBtn = document.querySelector(".close-btn");

// Event listener for the button to open the popup window
addProjectBtn.onclick = function() {
overlay.style.display = "block";
}

// Event listener for the button to close the popup window
closeBtn.onclick = function() {
overlay.style.display = "none";
}

// Get references to the DOM elements
const addProjectForm = document.getElementById("add-project-form");
const projectTable = document.getElementById("project-table");
const addProjectOverlay = document.getElementById("add-project-overlay");

// Event listener for the button to open the popup window
addProjectBtn.addEventListener("click", () => {
addProjectOverlay.style.display = "block";
});

// Event listener for the button to close the popup window
closeBtn.addEventListener("click", () => {
addProjectOverlay.style.display = "none";
});

// Event listener for the form submit event
addProjectForm.addEventListener("submit", (event) => {
	
// Prevent the form from submitting and refreshing the page
event.preventDefault();

// Get references to the input fields
const projectLeaderInput = document.getElementById("project-leader");
const projectTeamInput = document.getElementById("project-team");
const projectTitleInput = document.getElementById("project-title");
const deadlineInput = document.getElementById("deadline");

// Create a new table row with the input field data
const newRow = projectTable.insertRow(0);
newRow.innerHTML = `
<td>${projectLeaderInput.value}</td> 
<td>${projectTeamInput.value}</td> 
<td>${projectTitleInput.value}</td> 
<td>${deadlineInput.value}</td> 
`;
// Add the new row at the beginning of the table

projectTable.querySelector("tbody").insertBefore(newRow, projectTable.querySelector("tbody").firstChild);


// Clear the input fields

	projectLeaderInput.value = "";
	projectTeamInput.value = "";
	projectTitleInput.value = "";
	deadlineInput.value = "";

// Close the popup window
	addProjectOverlay.style.display = "none";
});

// Funktion zum Erstellen eines Hyperlink-Tags mit dem Projekt-Titel und Link zur anderen Seite
function createLink(projectTitle) {
  const link = document.createElement("a");
  link.href = "/src/main/resources/templates/Kanban.html"; // Link zur anderen Seite
  link.textContent = projectTitle;
  return link;
}

// Funktion, die alle Project-Titel in der Tabelle klickbar macht
function makeProjectTitlesClickable() {
  const projectTitleCells = document.querySelectorAll("#project-table tbody td:first-child");
  projectTitleCells.forEach(cell => {
    const projectTitle = cell.textContent;
    const link = createLink(projectTitle);
    cell.textContent = "";
    cell.appendChild(link);
  });
}

// Aufrufen der Funktion, sobald das Dokument geladen ist
document.addEventListener("DOMContentLoaded", makeProjectTitlesClickable);
