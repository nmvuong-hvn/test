import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/cupertino.dart';

import '../../constant/constants.dart';

class FirebaseAuthHelper{
  static FirebaseAuthHelper instance = FirebaseAuthHelper();
  final FirebaseAuth _auth = FirebaseAuth.instance;

  Stream<User?> get getAuthChange => _auth.authStateChanges();

  Future<bool> login(
      BuildContext context, String email, String password) async {
    try {
      showLoaderDialog(context);
      await _auth.signInWithEmailAndPassword(email: email, password: password);
      Navigator.of(context).pop(); //an dialog
      return true;
    } on FirebaseAuthException catch (e) {
      showMessage(e.message.toString());
      Navigator.of(context).pop();
      return false;
    }
  }

  Future<bool> signup(BuildContext context, String email, String password) async {
    try {
      showLoaderDialog(context);
      await _auth
          .createUserWithEmailAndPassword(email: email, password: password);
      Navigator.of(context).pop();
      return true;
    } on FirebaseAuthException catch (e) {
      showMessage(e.message.toString());
      Navigator.of(context).pop();
      return false;
    }
  }

  void signOut() async {
    await _auth.signOut();
  }
}