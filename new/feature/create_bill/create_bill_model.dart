import 'dart:ffi';

import 'package:flutter/cupertino.dart';

class DetailBillModel {
  late String idRoom;
  late String idBill;
  late String dateOfBill;
  late int moneyRoom;
  late int totalService;
  late int totalMoneyWater;
  late int totalMoneyElectric;
  late int numberElectricNew ;
  late int numberElectricOld;
  late int numberWaterNew ;
  late int numberWaterOld ;
  late int totalMoneyToPay ;
  late String  notes;


  DetailBillModel.name(
      this.idRoom,
      this.idBill,
      this.dateOfBill,
      this.moneyRoom,
      this.numberElectricNew,
      this.numberElectricOld,
      this.numberWaterNew ,
      this.numberWaterOld,
      this.totalService,
      this.totalMoneyWater,
      this.totalMoneyElectric,
      this.totalMoneyToPay,
      this.notes
      );

  DetailBillModel(
      this.idRoom,
      this.idBill,
      this.dateOfBill,
      this.moneyRoom,
      this.numberElectricNew,
      this.numberElectricOld,
      this.numberWaterNew ,
      this.numberWaterOld,
      this.totalService,
      this.totalMoneyWater,
      this.totalMoneyElectric,
      this.totalMoneyToPay,

      this.notes
      );

  Map<String, dynamic> toObjectMap() {
    return {
      'idBill': idBill,
      'idRoom': idRoom,
      'dateOfBill': dateOfBill,
      'moneyRoom': moneyRoom,
      'numberElectricNew': numberElectricNew,
      'numberElectricOld': numberElectricOld,
      'numberWaterNew': numberWaterNew,
      'numberWaterOld': numberWaterOld,
      'totalService': totalService,
      'totalMoneyWater': totalMoneyWater,
      'totalMoneyElectric': totalMoneyElectric,
      'totalMoneyToPay': totalMoneyToPay,
      'notes': notes,
    };
  }
  DetailBillModel.fromJson(Map<String,dynamic> json) {
    idBill = json['idBill'];
    idRoom = json['idRoom'];
    dateOfBill = json['dateOfBill'];
    moneyRoom = json['moneyRoom'];
    totalService = json['totalService']  ?? "";
    totalMoneyWater = json['totalMoneyWater'] ?? 0;
    totalMoneyElectric = json['totalMoneyElectric'] ?? 0;
    numberElectricNew = json['numberElectricNew'] ?? 0;
    numberWaterOld = json['numberWaterOld'] ?? 0;
    numberWaterNew = json['numberWaterNew'] ?? 0;
    numberElectricOld = json['numberElectricOld'] ?? 0;
    totalMoneyToPay = json['totalMoneyToPay'] ?? 0;
  }
}
