

import 'dart:io';

import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:datn/feature/create_electric_water/water_electric_bill_model.dart';
import 'package:datn/screen/home_details/room_model.dart';
import 'package:firebase_storage/firebase_storage.dart';
import 'package:get/get.dart';
import 'package:get_storage/get_storage.dart';
import 'package:image_picker/image_picker.dart';

class CreateElectricAndWaterController extends GetxController{

  final roomStore = FirebaseFirestore.instance;
  final roomsList = RxList<RoomModel>([]).obs;
  final billsList = RxList<WaterElectricBillModel>([]).obs;
  final imageStore = FirebaseStorage.instance;
  late WaterElectricBillModel infoRoom ;

  final imageElectricNewUrl = ''.obs;
  final imageElectricOldUrl = ''.obs;

  @override
  void onInit() async{
    // TODO: implement onInit
    super.onInit();
    await getListRooms();
    await getAllBills(roomsList.value[0].idRoom);

  }

  Future<void> getListRooms() async{
    final tempRoomsList = await roomStore.collection("Rooms").get();
    roomsList.value.clear();
    for(var roomModel in tempRoomsList.docs){
      RoomModel rModel = RoomModel.fromJson(roomModel.data());
      roomsList.value.add(rModel);
    }
  }
  Future getImageNew() async {
    final picker = ImagePicker();
    final image = await picker.pickImage(source: ImageSource.camera);
    if(image != null) {
      String nameFile = DateTime.now().microsecondsSinceEpoch.toString();
      final fileStore = imageStore.ref().child("images").child(nameFile);
      await fileStore.putFile(File(image.path));
      final urlImage = await fileStore.getDownloadURL();
      print("imageUrl = $urlImage");
      imageElectricNewUrl.value = urlImage ;
    }
  }

  Future getImageOld() async {
    final picker = ImagePicker();
    final image = await picker.pickImage(source: ImageSource.camera);
    if(image != null) {
      String nameFile = DateTime.now().microsecondsSinceEpoch.toString();
      final fileStore = imageStore.ref().child("images").child(nameFile);
      await fileStore.putFile(File(image.path));
      final urlImage = await fileStore.getDownloadURL();
      imageElectricOldUrl.value = urlImage ;
    }
  }
  void getInfoRooms(String idRoom) async {
    final tempInfoRoom = await roomStore.collection("Bills").doc(idRoom).get();
    if (tempInfoRoom.data() != null) {
      WaterElectricBillModel waterElectricBillModel = WaterElectricBillModel
          .fromJson(tempInfoRoom.data()!);
      infoRoom = waterElectricBillModel;
    }
  }

  Future<void> createElectricAndBill(String idRoom ,WaterElectricBillModel waterElectricBillModel) async{
    await roomStore.collection("Bills").doc("${idRoom}_${DateTime.now().microsecondsSinceEpoch}")
        .set(waterElectricBillModel.toBillJson());

    await getAllBills(idRoom);
  }
  Future<void> getAllBills(String idRoom) async {
    final tmpBillsList = await roomStore.collection("Bills").get();
    if(tmpBillsList != null && tmpBillsList.docs != null) {
      billsList.value.clear();
      for (var bill in tmpBillsList.docs) {
        WaterElectricBillModel dataModel = WaterElectricBillModel.fromJson(
            bill.data());
        print(dataModel);
        billsList.value.add(dataModel);
      }
    }
  }
}