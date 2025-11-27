<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reseller Login</title>

<style>
    :root {
        --z-purple: #5A189A;
        --z-pink:   #FF4B8B;
        --z-bg-dark: #050009;
    }

    * {
        box-sizing: border-box;
        margin: 0;
        padding: 0;
    }

    body {
        font-family: 'Poppins', sans-serif;
        background: radial-gradient(circle at top, #ff4b8b 0%, #2b0040 45%, var(--z-bg-dark) 100%);
        display: flex;
        justify-content: center;
        align-items: center;
        min-height: 100vh;
        color: #ffffff;
    }

    .login-shell {
        background: rgba(15, 1, 30, 0.96);
        width: 360px;
        max-width: 95vw;
        padding: 24px 22px 22px;
        border-radius: 22px;
        box-shadow: 0 18px 40px rgba(0, 0, 0, 0.75);
        border: 1px solid rgba(255, 255, 255, 0.08);
        backdrop-filter: blur(18px);
        animation: fadeIn 0.45s ease-out;
    }

    @keyframes fadeIn {
        from { opacity: 0; transform: translateY(18px); }
        to   { opacity: 1; transform: translateY(0); }
    }

    .header-row {
        display: flex;
        align-items: center;
        gap: 10px;
        margin-bottom: 10px;
    }

    .brand-icon {
        width: 34px;
        height: 34px;
        border-radius: 12px;
        background: radial-gradient(circle at 20% 20%, #ff69b4, #ff007a 50%, #7a1cff 100%);
        display: flex;
        justify-content: center;
        align-items: center;
        font-weight: 800;
        font-size: 18px;
        color: #fff;
        box-shadow: 0 0 14px rgba(255, 0, 122, 0.9);
    }

    .brand-text {
        display: flex;
        flex-direction: column;
    }

    .brand-title {
        font-size: 15px;
        font-weight: 700;
    }

    .brand-sub {
        font-size: 11px;
        opacity: 0.8;
    }

    h2 {
        text-align: left;
        color: #ffffff;
        margin: 8px 0 6px;
        font-size: 18px;
        font-weight: 700;
    }

    .hint-text {
        font-size: 12px;
        opacity: 0.75;
        margin-bottom: 10px;
    }

    .alert {
        margin-top: 6px;
        margin-bottom: 8px;
        padding: 9px 10px;
        border-radius: 10px;
        font-size: 12px;
    }

    .success {
        background: rgba(46, 213, 115, 0.15);
        border: 1px solid rgba(46, 213, 115, 0.7);
        color: #a3ffb9;
    }

    .error {
        background: rgba(255, 99, 132, 0.15);
        border: 1px solid rgba(255, 99, 132, 0.8);
        color: #ffb3c1;
    }

    form {
        margin-top: 8px;
    }

    label {
        font-weight: 500;
        color: #f4efff;
        font-size: 13px;
        display: block;
        margin-top: 10px;
        margin-bottom: 4px;
    }

    input[type="text"],
    input[type="password"] {
        width: 100%;
        padding: 11px 12px;
        border-radius: 14px;
        border: 2px solid rgba(192, 132, 252, 0.6);
        background: #120021;
        color: #ffffff;
        font-size: 14px;
        outline: none;
        transition: 0.2s ease;
    }

    input[type="text"]::placeholder,
    input[type="password"]::placeholder {
        color: rgba(255,255,255,0.45);
        font-size: 12px;
    }

    input[type="text"]:focus,
    input[type="password"]:focus {
        border-color: var(--z-pink);
        box-shadow: 0 0 0 3px rgba(255, 75, 139, 0.35);
        background: #19052b;
    }

    input[type="submit"] {
        width: 100%;
        padding: 12px;
        margin-top: 16px;
        background: linear-gradient(135deg, var(--z-purple), var(--z-pink));
        border: none;
        color: #fff;
        font-size: 15px;
        font-weight: 700;
        border-radius: 16px;
        cursor: pointer;
        transition: 0.18s;
        box-shadow: 0 10px 24px rgba(255, 75, 139, 0.4);
    }

    input[type="submit"]:hover {
        transform: translateY(-2px);
        box-shadow: 0 14px 30px rgba(255, 75, 139, 0.6);
    }

    .link-secondary {
        display: block;
        margin-top: 14px;
        text-align: center;
        text-decoration: none;
        color: #e4d5ff;
        font-weight: 500;
        font-size: 12px;
        opacity: 0.85;
        transition: 0.15s;
    }

    .link-secondary:hover {
        opacity: 1;
        text-decoration: underline;
    }

</style>
</head>
<body>

<div class="login-shell">

    <div class="header-row">
        <div class="brand-icon">Z</div>
        <div class="brand-text">
            <div class="brand-title">Reseller Login</div>
            <div class="brand-sub">Access your stock dashboard</div>
        </div>
    </div>

    <h2>Login as Reseller</h2>
    <div class="hint-text">Enter your registered email and password to continue.</div>

    <%
        String msg = (String) request.getAttribute("msg");
        String errorMsg = (String) request.getAttribute("errorMsg");

        if (msg != null) {
    %>
            <p class="alert success"><%= msg %></p>
    <%
        }
        if (errorMsg != null) {
    %>
            <p class="alert error"><%= errorMsg %></p>
    <%
        }
    %>

    <form method="post" action="<%= request.getContextPath() %>/login">
        <label>Email</label>
        <input type="text" name="email" placeholder="Enter your email"/>

        <label>Password</label>
        <input type="password" name="password" placeholder="Enter your password"/>

        <input type="submit" value="Login"/>
    </form>

    <a class="link-secondary" href="<%= request.getContextPath() %>/displayRegistrationForm">
        New here? Create a reseller account
    </a>

</div>

</body>
</html>
