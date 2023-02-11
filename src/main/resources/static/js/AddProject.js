 var addProjectBtn = document.getElementById("add-project-btn");
 var overlay = document.getElementById("add-project-overlay");
 var closeBtn = document.querySelector(".close-btn");

 addProjectBtn.onclick = function() {
   overlay.style.display = "block";
 }

 closeBtn.onclick = function() {
   overlay.style.display = "none";
 }
 // Referenzen auf die DOM-Elemente
 ;
 const addProjectForm = document.getElementById("add-project-form");
 const projectTable = document.getElementById("project-table");
 const addProjectOverlay = document.getElementById("add-project-overlay");

 // Event-Listener für den Button zum Öffnen des Popup-Fensters
 addProjectBtn.addEventListener("click", () => {
   addProjectOverlay.style.display = "block";
 });

 // Event-Listener für den Button zum Schließen des Popup-Fensters
 closeBtn.addEventListener("click", () => {
   addProjectOverlay.style.display = "none";
 });

 // Event-Listener für das Absenden des Formulars zum Hinzufügen von Projekten
 addProjectForm.addEventListener("submit", (event) => {
   // Verhindert das Standardverhalten des Formulars, damit die Seite nicht neu geladen wird
   event.preventDefault();
   // Referenzen auf die Eingabefelder
   const projectLeaderInput = document.getElementById("project-leader");
   const taskInput = document.getElementById("task");
   const statusInput = document.getElementById("status");
   const deadlineInput = document.getElementById("deadline");
   // Erstellt eine neue Tabellenzeile mit den Daten aus den Eingabefeldern
   const newRow = projectTable.insertRow(0);
   newRow.innerHTML = `
    <td><a href="#">${projectLeaderInput.value}</a></td>
    <td>${taskInput.value}</td>
    <td>${statusInput.value}</td>
    <td>${deadlineInput.value}</td>
  `;

   // Fügt die neue Zeile am Anfang der Tabelle hinzu
   projectTable.querySelector("tbody").insertBefore(newRow, projectTable.querySelector("tbody").firstChild);

   // Leert die Eingabefelder
   projectLeaderInput.value = "";
   taskInput.value = "";
   statusInput.value = "";
   deadlineInput.value = "";
   // Schließt das Popup-Fenster
   addProjectOverlay.style.display = "none";

 });
