<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Stock Form</title>

<style>
    :root {
        --z-purple: #5A189A;
        --z-pink:   #FF4B8B;
        --z-bg:     #050009;
    }

    * {
        box-sizing: border-box;
        margin: 0;
        padding: 0;
    }

    body {
        font-family: 'Poppins', sans-serif;
        background: radial-gradient(circle at top, #ff4b8b 0%, #2b0040 45%, var(--z-bg) 100%);
        margin: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        min-height: 100vh;
        color: #fff;
    }

    .stock-shell {
        background: rgba(15, 1, 30, 0.96);
        width: 380px;
        max-width: 95vw;
        padding: 24px 22px 20px;
        border-radius: 22px;
        box-shadow: 0 18px 40px rgba(0, 0, 0, 0.75);
        border: 1px solid rgba(255, 255, 255, 0.08);
        backdrop-filter: blur(18px);
        animation: slideUp 0.4s ease-out;
    }

    @keyframes slideUp {
        from { opacity: 0; transform: translateY(20px); }
        to   { opacity: 1; transform: translateY(0); }
    }

    .header {
        display: flex;
        align-items: center;
        gap: 10px;
        margin-bottom: 12px;
    }

    .icon-pill {
        width: 32px;
        height: 32px;
        border-radius: 12px;
        background: radial-gradient(circle at 20% 20%, #ff9a9e, #ff4b8b 50%, #7a1cff 100%);
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 18px;
        box-shadow: 0 0 14px rgba(255, 75, 139, 0.9);
        color:white;
    }

    .header-text {
        display: flex;
        flex-direction: column;
    }

    .header-title {
        font-size: 16px;
        font-weight: 700;
        color:white;
    }

    .header-sub {
        font-size: 11px;
        opacity: 0.8;
        color:white;
    }

    h2 {
        margin-top: 6px;
        font-size: 18px;
        font-weight: 700;
        color: #ffffff;
        color:white;
    }

    .hint {
        font-size: 12px;
        opacity: 0.75;
        margin-bottom: 10px;
        color:white;
    }

    .label {
        font-weight: 500;
        color: #f4efff;
        font-size: 13px;
        margin-bottom: 5px;
        margin-top: 10px;
        display: block;
        color:white;
    }

    .form-input {
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

    .form-input::placeholder {
        color: rgba(255, 255, 255, 0.45);
        font-size: 12px;
    }

    .form-input:focus {
        border-color: var(--z-pink);
        box-shadow: 0 0 0 3px rgba(255, 75, 139, 0.35);
        background: #19052b;
    }

    .btn-save {
        width: 100%;
        padding: 12px;
        margin-top: 18px;
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

    .btn-save:hover {
        transform: translateY(-2px);
        box-shadow: 0 14px 30px rgba(255, 75, 139, 0.6);
    }

    .back-link {
        display: block;
        text-align: center;
        margin-top: 14px;
        text-decoration: none;
        color: #e4d5ff;
        font-weight: 500;
        font-size: 13px;
        opacity: 0.85;
        transition: 0.15s;
    }

    .back-link:hover {
        opacity: 1;
        text-decoration: underline;
    }
</style>

</head>
<body>

<div class="stock-shell">

    <div class="header">
        <div class="icon-pill">S</div>
        <div class="header-text">
            <div class="header-title">Stock Form</div>
            <div class="header-sub">Add or Update Product Stock</div>
        </div>
    </div>

    <h2>Stock Details</h2>
    <div class="hint">Fill in the product information and save</div>

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

    <form:form method="post"
               action="${pageContext.request.contextPath}/stocks/save"
               modelAttribute="stock">

        <form:hidden path="id"/>

        <label class="label">Product Name</label>
        <form:input path="productName" cssClass="form-input" placeholder="Enter product name"/>
        <br/><br/>

        <label class="label">Quantity</label>
        <form:input path="quantity" cssClass="form-input" placeholder="Enter quantity"/>
        <br/><br/>

        <label class="label">Price</label>
        <form:input path="price" cssClass="form-input" placeholder="Enter price"/>
        <br/><br/>

        <input type="submit" value="Save Stock" class="btn-save"/>
    </form:form>

    <a href="${pageContext.request.contextPath}/stocks" class="back-link">
        Back to Stocks List
    </a>

</div>

</body>
</html>
