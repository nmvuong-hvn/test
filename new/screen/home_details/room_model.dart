class RoomModel {
  late String idRoom;
  late String idHome ;
  late String nameRoom;
  late String isEmpty;


  RoomModel(this.idRoom, this.idHome, this.nameRoom, this.isEmpty);


  RoomModel.name(this.idRoom, this.idHome, this.nameRoom, this.isEmpty);

  RoomModel.fromJson(Map<String, dynamic> dataMap) {
    idRoom = dataMap['idRoom'];
    idHome = dataMap['idHome'];
    nameRoom = dataMap['nameRoom'];
    isEmpty = dataMap['isEmpty'];
  }

  Map<String, dynamic> toRoomMap() {
    return {'idRoom': idRoom,
      'nameRoom': nameRoom,
      'idHome': idHome,
      'isEmpty': isEmpty
    };
  }

  @override
  String toString() {
    return 'RoomModel{idRoom: $idRoom, idHome: $idHome, nameRoom: $nameRoom}';
  }
}
