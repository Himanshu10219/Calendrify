<!DOCTYPE html>
<html>
<head>


<style>
body {
font-family: Arial, sans-serif;
margin: 0;
padding: 0;
background-color: #f2f2f2;
}

.container {
max-width: 600px;
margin: 50px auto;
padding: 20px;
border: 1px solid #043a4d;
border-radius: 5px;
background-color: #043a4d;
box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
text-align: center;
}

h1 {
font-size: 28px;
margin-bottom: 20px;

color: white;
}

.logo {
display: block;
margin: 0 auto 20px;
width: 150px;
}

.card {
background-color: white;
border-radius: 5px;
padding: 20px;
margin-bottom: 20px;
}

.card-header {
font-size: 20px;
font-weight: bold;
margin-bottom: 10px;
color: black;
}

.card-content {
font-size: 16px;
text-align: left;
}

.card-content p {
margin: 10px 0;
}

.card-content strong {
font-weight: bold;
}

.card-footer {
text-align: center;
margin-top: 20px;
}
</style>
</head>
<body>
<div class="container">

    <img class="logo" src="https://firebasestorage.googleapis.com/v0/b/calendar-c198f.appspot.com/o/Calendar%20-Logo%20(1).png?alt=media&token=2a1d7100-c7b9-44a8-b28f-e7637f797c55&_gl=1*1dpbdmw*_ga*MzUyODYwLjE2ODEwNDE4ODE.*_ga_CW55HF8NVT*MTY4NjA1MzQ3NS44LjEuMTY4NjA1MzU4MC4wLjAuMA.." alt="Logo">

    <h1>Calendrify </h1>

    <div class="card">

      <div class="card-header">Complaint</div>

      <div class="card-content">

        <p style="color: black;" ><strong>Name:</strong> ${name}</p>

        <p style="color: black;"><strong>Email:</strong> ${email}</p>

		<p style="color: black;"><strong>Contact:</strong> ${contact}</p>

        <p style="color: black;"><strong>Date:</strong> ${date}</p>

        <p style="color: black;"><strong>Message:</strong> ${message}</p>
      </div>
    </div>
  </div>
</body>
</html>