import 'dart:ffi';

class WaterElectricBillModel {
  late String id ;
  late int timeStamp ;
  late String idRoom ;
  late int numberElectric;
  late int numberWater ;
  late String dateTime ;
  late String urlElectricImageNew;
  late String urlElectricImageOld;

  WaterElectricBillModel.name(
      this.id,
      this.timeStamp,
      this.idRoom,
      this.numberElectric,
      this.numberWater,
      this.dateTime,
      this.urlElectricImageNew,
      this.urlElectricImageOld);

  WaterElectricBillModel(
      this.id,
      this.timeStamp,
      this.idRoom,
      this.dateTime,
      this.numberElectric,
      this.numberWater,
      this.urlElectricImageNew,
      this.urlElectricImageOld);

  WaterElectricBillModel.fromJson(Map<String, dynamic> dataMap){
    id = dataMap['id'];
    idRoom = dataMap['idRoom'];
    timeStamp = dataMap['timeStamp'];
    numberElectric = dataMap['numberElectric'];
    numberWater = dataMap['numberWater'];
    dateTime = dataMap['dateTime'];
    urlElectricImageNew = dataMap['urlElectricImageNew'];
    urlElectricImageOld = dataMap['urlElectricImageOld'];
  }

  Map<String, dynamic> toBillJson(){
    return {
      'id': id,
      'idRoom': idRoom,
      'timeStamp': timeStamp,
      'dateTime': dateTime,
      'numberWater': numberWater,
      'numberElectric': numberElectric,
      'urlElectricImageNew': urlElectricImageNew,
      'urlElectricImageOld': urlElectricImageOld,
    };
  }

  @override
  String toString() {
    return 'WaterElectricBillModel{id: $id, timeStamp: $timeStamp, idRoom: $idRoom, numberElectric: $numberElectric, numberWater: $numberWater, dateTime: $dateTime, urlElectricImageNew: $urlElectricImageNew, urlElectricImageOld: $urlElectricImageOld}';
  }
}