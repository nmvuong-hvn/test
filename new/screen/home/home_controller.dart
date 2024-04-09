import 'dart:collection';

import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:datn/screen/add_person/person_model.dart';
import 'package:datn/screen/home/home_model.dart';
import 'package:datn/screen/home_details/room_model.dart';
import 'package:get/get.dart';

class HomeController extends GetxController {
  final homeStore = FirebaseFirestore.instance;
  final homesList = RxList<HomeModel>([]).obs;
  final roomList = RxList<RoomModel>([]).obs;
  final searchList = RxList<HomeModel>();
  final hashMap = <String, List<RoomModel>>{}.obs;

  late HomeModel homeModel;
  final numberEmpty  = <String, int>{}.obs ;
  final numberShortage  = <String, int>{}.obs ;
  final numberMoney  = <String, int>{}.obs ;

  @override
  void onInit() async {
    // TODO: implement onInit
    await getListHome();
    await getInfoHome();
    super.onInit();

    Future.delayed(const Duration(seconds: 3));
  }

  void createHome(HomeModel home) {
    var idHome = DateTime.timestamp().millisecondsSinceEpoch;
    home.idHome = "$idHome";
    homeStore.collection("Homes").doc("$idHome").set(home.toHomeMap());
    homesList.value.add(home);
    Get.snackbar("Thành công", "Thêm thành công nhà trọ",
        snackPosition: SnackPosition.BOTTOM);
  }
  Future<void> getInfoHome() async {
    var tempRoomsList = await homeStore.collection("Rooms").get();
    roomList.value.clear();
    for (var roomModel in tempRoomsList.docs) {
      var objRoomModel = RoomModel.fromJson(roomModel.data());
      roomList.value.add(objRoomModel);
    }
    hashMap.clear();
    numberEmpty.clear();
    for (var home in homesList.value) {
      final tmpRoomList =
          roomList.value.where((room) => room.idHome == home.idHome).toList();
      if (tmpRoomList.isNotEmpty) {
        hashMap.value[home.idHome] = tmpRoomList;
        final dataList = tmpRoomList.where((e) => e.isEmpty.contains("false")).toList();
        numberEmpty[home.idHome] = dataList.length;
      }else {
        hashMap.value[home.idHome] = List.empty();
        numberEmpty[home.idHome] = 0;
      }
    }
  }


  Future<void> getListHome() async {
    var tempHomeList = await homeStore.collection("Homes").get();
    homesList.value.clear();
    for (var homeModel in tempHomeList.docs) {
      var objHomeModel = HomeModel.fromJson(homeModel.data());
      print(objHomeModel);
      homesList.value.add(objHomeModel);
    }
  }

  Future<void> searchListHome(String name) async {
      var filteredHomes = homesList.value.where((home) =>
          home.nameHome.toLowerCase().contains(name.toLowerCase())).toList();
      searchList.value = filteredHomes;
      searchList.refresh();
  }

  void selectedHome(HomeModel homeModel) {
    this.homeModel = homeModel;
  }

}
