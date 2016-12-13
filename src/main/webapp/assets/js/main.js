// Document ready
$(document).ready(function() {
    $(".dropdown-toggle").dropdown();

    $('button[type=reset]').click(function() {
        $(this).closest('form').find('input, textarea').val("");

        return false;
    });

    // validate that the user profile is correct
    $('form.user-profile').submit(function(event) {
        var errors = [];
        console.log('here2');

        if ($('input[name=name]').val().length <= 0) {
            errors.push("Username cannot be blank");
        }

        if ($('input[name=email]').val().length <= 0) {
            errors.push("Email cannot be blank");
        }

        // display all errors
        if (errors.length) {
            alert(errors.join('\n'));

            // do not submit form
            return false;
        }

        return true;
    });

    // confirm the admin wants to delete the user
    $('a.delete-user').click(function(event) {
        return confirm("Are you sure you want to delete this user?");
    });

    // validate that the user login is correct
    $('form.user-login').submit(function(event) {
        if ($('input[name=name]').val().length <= 0) {
            alert("Username cannot be blank");

            return false;
        }

        return true;
    });
});
