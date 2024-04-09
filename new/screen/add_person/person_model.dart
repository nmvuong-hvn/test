
class PersonModel {
  late String id ;
  late String name ;
  late String phone;
  late String  address;
  late String  identification;
  late String  urlIdentifyBefore;
  late String  urlIdentifyAfter;
  late String  numberPhone;
  late String  dayOfRent ;
  late String  numberMoney;
  late String  note ;

  PersonModel(
      this.id,
      this.name,
      this.phone,
      this.address,
      this.identification,
      this.urlIdentifyBefore,
      this.urlIdentifyAfter,
      this.numberPhone,
      this.dayOfRent,
      this.numberMoney,
      this.note);

  Map<String, dynamic> toPersonModel(){
    return {
      'id' : id ,
      'name' : name ,
      'phone' : phone ,
      'address' : address ,
      'identification' : identification ,
      'urlIdentifyBefore' : urlIdentifyBefore ,
      'urlIdentifyAfter' : urlIdentifyAfter ,
      'numberPhone' : numberPhone ,
      'dayOfRent' : dayOfRent ,
      'numberMoney' : numberMoney ,
      'note' : note ,
    };
  }
  PersonModel.fromJson(Map<String, dynamic> dataMap){
    id = dataMap['id'];
    name = dataMap['name'];
    phone = dataMap['phone'];
    address = dataMap['address'];
    identification = dataMap['identification'];
    urlIdentifyBefore = dataMap['urlIdentifyBefore'];
    urlIdentifyAfter = dataMap['urlIdentifyAfter'];
    numberPhone = dataMap['numberPhone'];
    dayOfRent = dataMap['dayOfRent'];
    numberMoney = dataMap['numberMoney'];
    note = dataMap['note'];
  }

}