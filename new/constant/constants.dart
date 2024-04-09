import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';

import 'color.dart';


void showMessage(String message) {
  Fluttertoast.showToast(
      msg: message,
      toastLength: Toast.LENGTH_SHORT,
      backgroundColor: Colors.grey,
      textColor: Colors.white,
      fontSize: 16.0);
}

void showLoaderDialog(BuildContext context) {
  AlertDialog alert = AlertDialog(
    content: Builder(builder: (context) {
      return SizedBox(
        width: 100,
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            CircularProgressIndicator(
              color: ColorInstance.backgroundColor,
            ),
            const SizedBox(
              height: 18.0,
            ),
            Container(
                margin: const EdgeInsets.only(left: 7),
                child: const Text("Loading")),
          ],
        ),
      );
    }),
  );
  showDialog(
    barrierDismissible: false,
    context: context,
    builder: (BuildContext context) {
      return alert;
    },
  );
}

bool loginValidation(BuildContext context, String email, String password) {
  if (email.isEmpty && password.isEmpty) {
    showMessage("Dữ liệu không được để trống !");
    return false;
  } else if (email.isEmpty) {
    showMessage("E-mail không được để trống !");
    return false;
  } else if (password.isEmpty) {
    showMessage("Mật khẩu không được để trống !");
    return false;
  } else {
    return true;
  }
}

bool emailValidation(BuildContext context, String email) {
  if (email.isEmpty) {
    showMessage("Email không được để trống");
    return false;
  } else {
    return true;
  }
}

bool signupValidation(BuildContext context, String email, String password) {
  if (email.isEmpty && password.isEmpty) {
    showMessage("Dữ liệu không được để trống !");
    return false;
  } else if (email.isEmpty) {
    showMessage("E-mail không được để trống !");
    return false;
  } else if (email.isEmpty) {
    showMessage("Mật khẩu không được để trống !");
    return false;
  }else {
    return true;
  }
}

enum Item {
  Change ,
  Order
}