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

// Create a new table row with the input field data
const newRow = projectTable.insertRow(0);
newRow.innerHTML = `
<td>${projectTitleInput.value}</td> 
<td>${projectLeaderInput.value}</td> 
<td>${projectTeamInput.value}</td> 
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

// Select all edit project buttons and add event listener
const editProjectBtns = document.querySelectorAll('.edit-project-btn');
editProjectBtns.forEach(editProjectBtn => {
    editProjectBtn.addEventListener('click', () => {
        // Replace "#" with the URL of your edit project page
        window.location.href = '/src/main/resources/templates/Kanban.html';
    });
});

// Select all delete project buttons and add event listener
const deleteProjectBtns = document.querySelectorAll('.delete-project-btn');
deleteProjectBtns.forEach(deleteProjectBtn => {
    deleteProjectBtn.addEventListener('click', () => {
        // Get the row of the clicked delete project button
        const row = deleteProjectBtn.parentNode.parentNode;
        // Remove the row from the table
        row.parentNode.removeChild(row);
        // Here you can write code to delete the project from the database using AJAX
    });
});
