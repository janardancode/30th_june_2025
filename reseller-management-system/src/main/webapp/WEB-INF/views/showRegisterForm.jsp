<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Reseller Registration</title>

<style>
:root {
  --z-purple: #5A189A;
  --z-pink: #FF4B8B;
  --z-bg: #f8f2ff;
  --z-text: #2b0040;
}

body {
  font-family: "Poppins", sans-serif;
  background: var(--z-bg);
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  margin: 0;
  padding: 20px;
}

.register-card {
  background: #fff;
  width: 360px;
  max-width: 95vw;
  padding: 26px 22px;
  border-radius: 20px;
  box-shadow: 0 12px 32px rgba(90,24,154,0.22);
  border: 1.5px solid rgba(90,24,154,0.08);
  animation: popIn 0.4s ease-out;
}

@keyframes popIn {
  from { opacity: 0; transform: scale(0.95) translateY(10px); }
  to { opacity: 1; transform: scale(1) translateY(0); }
}

.card-title {
  text-align: center;
  color: var(--z-text);
  font-size: 22px;
  font-weight: 700;
  margin-bottom: 18px;
  letter-spacing: 0.3px;
}

.form-label {
  font-size: 13px;
  font-weight: 600;
  color: var(--z-text);
  margin-bottom: 5px;
  display: block;
  opacity: 0.85;
}

.form-input {
  width: 100%;
  padding: 12px 14px;
  border: 2px solid #e0c3fc;
  border-radius: 14px;
  font-size: 14px;
  transition: 0.2s ease;
  background: #faf5ff;
}

.form-input:focus {
  border-color: var(--z-purple);
  box-shadow: 0 0 0 4px rgba(90,24,154,0.12);
  outline: none;
  background: #fff;
}

.btn-register {
  width: 100%;
  background: linear-gradient(135deg, var(--z-purple), var(--z-pink));
  padding: 13px;
  border-radius: 14px;
  border: none;
  color: #fff;
  font-size: 16px;
  font-weight: 700;
  margin-top: 20px;
  cursor: pointer;
  transition: 0.2s;
  box-shadow: 0 8px 22px rgba(255,75,139,0.35);
}

.btn-register:hover {
  transform: translateY(-2px);
  box-shadow: 0 12px 28px rgba(255,75,139,0.5);
}

.footer-link {
  text-align: center;
  margin-top: 14px;
  font-size: 12px;
  font-weight: 600;
  color: var(--z-purple);
  text-decoration: none;
  display: block;
  opacity: 0.8;
}
.footer-link:hover {
  opacity: 1;
}
</style>
</head>

<body>

<div class="register-card">
    <h2 class="card-title">Register as Reseller</h2>

    <form:form method="post"
               action="${pageContext.request.contextPath}/register"
               modelAttribute="reseller">

        <label class="form-label">First Name</label>
        <form:input path="firstName" cssClass="form-input"/>

        <label class="form-label">Last Name</label>
        <form:input path="lastName" cssClass="form-input"/>

        <label class="form-label">Email</label>
        <form:input path="email" type="email" cssClass="form-input"/>

        <label class="form-label">Mobile</label>
        <form:input path="mobile" cssClass="form-input"/>

        <label class="form-label">Password</label>
        <form:password path="password" cssClass="form-input"/>

        <input type="submit" value="Register" class="btn-register"/>

    </form:form>

    <a class="footer-link" href="${pageContext.request.contextPath}/login">Already user? Login</a>
</div>

</body>
</html>
