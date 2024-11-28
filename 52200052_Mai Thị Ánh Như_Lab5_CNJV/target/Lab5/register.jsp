<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css    ">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</head>

<body class="bg-secondary">

<h3 class="text-center my-5 text-light">Account Registration</h3>

<!-- buộc toàn bộ dòng và cột phải bỏ trong class container -->
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-10 col-lg-8 col-xl-5">
            <div class="border p-3 rounded bg-light">
                <div class="form-group">
                    <div class="alert alert-danger alert-dismissible fade show">
                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                        <% if (request.getAttribute("error") != null) { %>
                        <div style="text-align: center" class="error">
                            <%= request.getAttribute("error") %>
                        </div>
                    </div>
                </div>
                <% } %>
                <form method="post" action="register">
                    <div class="form-group">
                        <label for="username">Fullname</label>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                  <span class="input-group-text">
                    <i class="fas fa-user"></i>
                  </span>
                            </div>
                            <input id="username" name="username" type="text" class="form-control"
                                   placeholder="Your Name">
                        </div>

                        <div class="form-group">
                            <label for="email">Email</label>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                  <span class="input-group-text">
                    <i class="fas fa-user"></i>
                  </span>
                                </div>
                                <input id="email" name="email" type="text" class="form-control" placeholder="Email">
                            </div>

                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                  <span class="input-group-text">
                    <i class="fas fa-lock"></i>
                  </span>
                                </div>
                                <input id="password" name="password" type="password" class="form-control"
                                       placeholder="Password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="confirmPassword">Confirm Password</label>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                  <span class="input-group-text">
                    <i class="fas fa-lock"></i>
                  </span>
                                </div>
                                <input id="confirmPassword" name="confirmPassword" type="password" class="form-control"
                                       placeholder="Password">
                            </div>
                        </div>

                        <div class="form-group">
                            <button class="btn btn-success px-5" value="Submit" type="submit">Register</button>
                        </div>
                        <div class="form-group">
                            <p>Already have an account? <a href="login.jsp">Login now!</a></p>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>

</html>