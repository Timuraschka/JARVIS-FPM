const columns = document.querySelectorAll('.column');
let draggedItem = null;

for (const column of columns) {
  column.addEventListener('dragstart', function(event) {
    draggedItem = event.target;
    event.target.style.opacity = .5;
  });

  column.addEventListener('dragend', function(event) {
    event.target.style.opacity = "";
    draggedItem = null;
  });

  column.addEventListener('dragover', function(event) {
    event.preventDefault();
  });

  column.addEventListener('dragenter', function(event) {
    event.target.style.backgroundColor = "rgba(0, 0, 0, 0.2)";
  });

  column.addEventListener('dragleave', function(event) {
    event.target.style.backgroundColor = "";
  });

  column.addEventListener('drop', function(event) {
    event.target.style.backgroundColor = "";
    event.target.appendChild(draggedItem);
  });
}
var createTicketBtn = document.getElementById("create-ticket-btn");
var overlay = document.getElementById("create-ticket-overlay");
var closeBtn = document.querySelector(".close-btn");

createTicketBtn.onclick = function() {
  overlay.style.display = "block";
}

closeBtn.onclick = function() {
  overlay.style.display = "none";
}

const createTicketForm = document.getElementById("create-ticket-form");
const addTicketPopup = document.getElementById("add-ticket-overlay");




// Open the popup window when the create ticket button is clicked
createTicketBtn.addEventListener("click", function() {
  addTicketPopup.style.display = "block";
});

// Close the popup window when the close button is clicked
closeBtn.addEventListener("click", function() {
  addTicketPopup.style.display = "none";
});


// Get the form element
const form = document.getElementById('create-ticket-form');

// Get the backlog column
const backlogColumn = document.getElementById('backlog');

// Submit event listener for the form
form.addEventListener('submit', (event) => {
  event.preventDefault();

  // Get the input values from the form
  const assignee = document.getElementById('assignee').value;
  const task = document.getElementById('task').value;
  const priority = document.getElementById('priority').value;

  // Create a new card with the input values
  const newCard = document.createElement('div');
  newCard.classList.add('card');
  newCard.setAttribute('draggable', 'true');



  newCard.innerHTML = `Assignee: ${assignee} <br> Task: ${task} <br> Priority: ${priority}`;


  // Append the new card to the backlog column
  backlogColumn.insertBefore(newCard, backlogColumn.secondChild);
  // Clear the input fields
  form.reset();




  // Close the popup window
  document.getElementById("create-ticket-overlay").style.display = "none";
});

  // Fetch projects from database
  fetch('/api/projects')
    .then(response => response.json())
    .then(projects => {
      // Create a dropdown menu for projects
      const projectSelect = document.createElement('select');
      projectSelect.id = 'project-select';

      // Add options to the dropdown menu for each project
      projects.forEach(project => {
        const option = document.createElement('option');
        option.value = project.id;
        option.text = project.name;
        projectSelect.add(option);
      });

      // Add the dropdown menu above the create ticket button
      const createTicketBtn = document.getElementById('create-ticket-btn');
      createTicketBtn.parentNode.insertBefore(projectSelect, createTicketBtn);
    });


