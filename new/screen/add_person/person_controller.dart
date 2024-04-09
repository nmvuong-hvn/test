
import 'dart:io';

import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:datn/screen/add_person/person_model.dart';
import 'package:datn/screen/home_details/room_model.dart';
import 'package:firebase_storage/firebase_storage.dart';
import 'package:get/get.dart';
import 'package:image_picker/image_picker.dart';

class PersonController extends GetxController {

  final personStore = FirebaseFirestore.instance;
  final imageStore = FirebaseStorage.instance;

  final imageCCCDBefore ="".obs;
  final imageCCCDAfter = "".obs;

  Future<void> addPerson(String idRoom, PersonModel personModel, Map<String, dynamic> roomModel) async {
    personStore.collection("Customers").doc(idRoom)
        .set(personModel.toPersonModel()).whenComplete(() =>
        Get.snackbar("Thành công", "Thêm thành công")
    );
    personStore.collection("Rooms").doc(idRoom).update(roomModel);
  }
  Future getImageAfter() async {
    final picker = ImagePicker();
    final image = await picker.pickImage(source: ImageSource.camera);
    if(image != null) {
      String nameFile = DateTime.now().microsecondsSinceEpoch.toString();
      final fileStore = imageStore.ref().child("images").child(nameFile);
      await fileStore.putFile(File(image.path));
      final urlImage = await fileStore.getDownloadURL();
      imageCCCDAfter.value = urlImage ;
    }
  }

  Future getImageBefore() async {
    final picker = ImagePicker();
    final image = await picker.pickImage(source: ImageSource.camera);
    if(image != null) {
      String nameFile = DateTime.now().microsecondsSinceEpoch.toString();
      final fileStore = imageStore.ref().child("images").child(nameFile);
      await fileStore.putFile(File(image.path));
      final urlImage = await fileStore.getDownloadURL();
      imageCCCDBefore.value = urlImage ;
    }
  }
}