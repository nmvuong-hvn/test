
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:datn/screen/home_details/room_model.dart';
import 'package:get/get.dart';

import '../add_person/person_model.dart';

class HomeDetailsController extends GetxController{
  
  final roomStore = FirebaseFirestore.instance;
  final roomList = RxList<RoomModel>([]).obs;
  final searchList = RxList<RoomModel>();
  final personMap = RxMap<String,PersonModel>().obs;
  final emptyRoomList = RxList<RoomModel>([]).obs;
  final personStore = FirebaseFirestore.instance;

  @override
  void onInit() async {
    // TODO: implement onInit
    super.onInit();
    await getAllRoom(Get.parameters['idHome'] ?? "");
    await getCustomerInfoRoom();
  }

  Future<void> searchListRoom(String name) async {
    String searchQuery = name.toLowerCase();
    var filteredRooms = roomList.value.where((room) {
      String personName = personMap.value[room.idRoom]?.name ?? "";
      bool matchesPersonName = personName.toLowerCase().contains(searchQuery);
      return matchesPersonName;
    }).toList();
    searchList.value = filteredRooms;
    searchList.refresh();
  }

  void addRoom(RoomModel roomModel) {
      roomModel.idRoom = "${DateTime.timestamp().microsecondsSinceEpoch}";
      roomStore.collection("Rooms").doc(roomModel.idRoom).set(roomModel.toRoomMap());
      Get.snackbar("Thành công", "Thêm phòng thành công");
      roomList.value.add(roomModel);
  }

  Future<void> getAllRoom(String id) async {
    var tempRoomList = await roomStore.collection("Rooms").get();
    roomList.value.clear();
    for (var roomData in tempRoomList.docs) {
      RoomModel roomModel = RoomModel.fromJson(roomData.data());
      if(roomModel.idHome == id) {
        roomList.value.add(roomModel);
      }
    }
  }
  
  Future<void> getCustomerInfoRoom() async {

    try {
      for(var roomModel in roomList.value.toList()) {
        var tmpCustomerInfo = await roomStore.collection("Customers")
            .doc(roomModel.idRoom).get();
        if (tmpCustomerInfo.exists) {
          personMap.value[roomModel.idRoom] =
              PersonModel.fromJson(tmpCustomerInfo.data()!);
        }else {
            emptyRoomList.value.add(roomModel);
        }
      }
    }on Exception catch (_, e){
        print("Error = $e");
    }

  }

  Future<void> addPerson(String idRoom, PersonModel personModel) async {
    personStore.collection("Customers").doc(idRoom)
        .set(personModel.toPersonModel()).whenComplete(() =>
        Get.snackbar("Thành công", "Thêm thành công")
    );
  }
  Future<void> changeInfoRoom(RoomModel room) async{
    if(emptyRoomList.value.isNotEmpty && emptyRoomList.value.length == 1) {
      // roomList.value.removeWhere((element) => element.idRoom == room.idRoom);
      personMap.value.remove(room.idRoom);
      print("roomList = ${roomList.value}" );
      final customerRoom = await roomStore.collection("Customers").doc(room.idRoom).get();
      if(customerRoom.exists && customerRoom.data() != null){
        final personObj = PersonModel.fromJson(customerRoom.data()!);
        await personStore.collection("Customers").doc(room.idRoom).delete();
        await personStore.collection("Customers").doc(emptyRoomList.value[0].idRoom)
        .set(personObj.toPersonModel());
        personMap.value[emptyRoomList.value[0].idRoom] = personObj;
      }
      // roomList.value.addAll(emptyRoomList.value);
      emptyRoomList.value.clear();
      emptyRoomList.value.add(room);
      print("roomList = ${roomList.value}" );

    }
  }

}