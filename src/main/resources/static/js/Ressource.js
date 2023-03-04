// Get the button element
const addRessourceBtn = document.getElementById("add-ressource-btn");

// Add a click event listener to the button
addRessourceBtn.addEventListener("click", function() {
  // Get the form and overlay elements
  const addRessourceForm = document.getElementById("add-ressource-form");
  const addRessourceOverlay = document.getElementById("add-ressource-overlay");

  // Show the overlay
  addRessourceOverlay.style.display = "block";

  // Add a submit event listener to the form
  addRessourceForm.addEventListener("submit", function(event) {
    // Prevent the form from submitting
    event.preventDefault();

    // Get the input values
    const ressourceName = document.getElementById("ressource").value;
    const team = document.getElementById("team").value;
    const hourlyRate = document.getElementById("hourlyRate").value;
    const involvedTasks = document.getElementById("involvedTasks").value;

    // Create a new table row and cells
    const table = document.getElementById("ressource-list");
    const newRow = table.insertRow(1);
    const nameCell = newRow.insertCell(0);
    const teamCell = newRow.insertCell(1);
    const rateCell = newRow.insertCell(2);
    const tasksCell = newRow.insertCell(3);

    // Set the cell values
    nameCell.innerHTML = ressourceName;
    teamCell.innerHTML = team;
    rateCell.innerHTML = hourlyRate;
    tasksCell.innerHTML = involvedTasks;

    // Clear the input fields
    document.getElementById("ressource").value = "";
    document.getElementById("team").value = "";
    document.getElementById("hourlyRate").value = "";
    document.getElementById("involvedTasks").value = "";
    
    // Hide the overlay
    addRessourceOverlay.style.display = "none";
  });

  // Add a click event listener to the close button
  const closeBtn = addRessourceForm.getElementsByClassName("close-btn")[0];
  closeBtn.addEventListener("click", function() {
    addRessourceOverlay.style.display = "none";
  });
});
