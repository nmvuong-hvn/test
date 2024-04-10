import 'package:flutter/material.dart';

class CommonDataTable extends StatefulWidget {

  String col1 ;
  String col2 ;
  String col3 ;
  String col4 ;

  CommonDataTable({super.key, required this.col1, required this.col2, required this.col3, required this.col4});

  @override
  State<CommonDataTable> createState() => _CommonDataTableProState();
}

class _CommonDataTableProState extends State<CommonDataTable> {

  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.start,
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Row(
          mainAxisAlignment: MainAxisAlignment.start,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Expanded(flex: 1, child: Text(widget.col1)),
            Expanded(flex: 1, child: Text(widget.col2)),
            Expanded(flex: 1, child: Text(widget.col3)),
            Expanded(flex: 1, child: Text(widget.col4)),
          ],
        ),
        const Divider(height: 1,)
      ],
    ) ;
  }
}