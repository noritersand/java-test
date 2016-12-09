<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- if HTML5 -->
<!-- meta charset=UTF-8" -->
<title></title>

<!-- 공통요소 -->
<link rel="stylesheet" type="text/css" href="./ui/arongi/AXJ.css" />
<script type="text/javascript" src="./jquery/jquery.min.js"></script>
<script type="text/javascript" src="./lib/AXJ.js"></script>

<!-- 추가하는 UI 요소 -->
<link rel="stylesheet" type="text/css" href="./ui/arongi/AXInput.css" />
<link rel="stylesheet" type="text/css" href="./ui/arongi/AXSelect.css" />
<link rel="stylesheet" type="text/css" href="./ui/arongi/AXGrid.css" />
<script type="text/javascript" src="./lib/AXInput.js"></script>
<script type="text/javascript" src="./lib/AXSelect.js"></script>
<script type="text/javascript" src="./lib/AXGrid.js"></script>

<script>
// var myGrid = new AXGrid(); // instance
// var fnObj = {
//     pageStart: function(){

//         myGrid.setConfig({
//             targetID : "AXGridTarget",
//             theme : "AXGrid",
//             autoChangeGridView: { // autoChangeGridView by browser width
//                 mobile:[0,600], grid:[600]
//             },
//             colGroup : [
//                 {key:"no", label:"No.", width:"40", align:"center", formatter:"money"},
//                 {key:"title", label:"Title", width:"200"},
//                 {key:"writer", label:"Writer", width:"100", align:"center"},
//                 {key:"date", label:"Date.", width:"100", align:"center"},
//                 {key:"desc", label:"Etc.", width:"*"}
//             ],
//             body : {
//                 onclick: function(){
//                     toast.push(Object.toJSON({index:this.index, item:this.item}));
//                 }
//             },
//             page:{
//                 paging:false
//             }
//         });

//         var list = [
//             {
//                 no:1, title:"AXGrid 첫번째 줄 입니다.AXGrid 첫번째 줄 입니다.", writer:"장기영", img:"img/1.jpg", desc:"많은 글을 담고 있는 내용 입니다. 자연스럽게 줄이 넘어가고 표현되는 것이 관건 입니다.", category:"액시스제이", date:"2014-04-05"
//             },
//             {
//                 no:2, title:"AXGrid 두번째 줄 입니다.AXGrid 첫번째 줄 입니다.", writer:"장기영", img:"img/2.jpg", desc:"많은 글을 담고 있는 내용 입니다.", category:"액시스제이", date:"2014-04-07"
//             },
//             {
//                 no:3, title:"AXGrid 세번째 줄 입니다.AXGrid 첫번째 줄 입니다.", writer:"장기영", img:"img/3.jpg", desc:"많은 글을 담고 있는 내용 입니다. 자연스럽게...", category:"액시스제이", date:"2014-04-09"
//             }
//         ];

//         //setList
//         myGrid.setList(list);
//         /* ajax way
//         myGrid.setList({
//             ajaxUrl:"...",
//             ajaxPars:"",
//             onLoad:function(){},
//             onError:function(){}
//         });
//         */
//     }
// };
// jQuery(document).ready(fnObj.pageStart.delay(0.1));

        /**
         * Require Files for AXISJ UI Component...
         * Based        : jQuery
         * Javascript    : AXJ.js, AXGrid.js, AXInput.js, AXSelect.js
         * CSS            : AXJ.css, AXGrid.css, AXButton.css, AXInput.css, AXSelect.css
         */
        var pageID = "inline-edit";
        var fnObj = {
                    pageStart: function () {
                        fnObj.grid.bind();
                    },
                    grid: {
                        target: new AXGrid(),
                        bind: function () {
                            window.myGrid = fnObj.grid.target;
 
                            myGrid.setConfig({
                                        targetID: "AXGridTarget",
                                        sort: false,
                                        fixedColSeq: 3,
                                        colGroup: [
                                            {key: "no", label: "번호", width: "50", align: "center", formatter: "checkbox"},
                                            {
                                                key: "_CUD", label: "상태", width: "50", align: "center"
                                            },
                                            {
                                                key: "string", label: "String", width: "200",
                                                formatter: function (val) {
                                                    if (Object.isObject(this.value)) {
                                                        return this.value.NM;
                                                    }
                                                    else {
                                                        return this.value;
                                                    }
                                                },
                                                editor: {
                                                    type: "AXSelector",
                                                    config: {
                                                        reserveKeys: {
                                                            options: "list",
                                                            optionValue: "CD",
                                                            optionText: "NM"
                                                        },
                                                        ajaxUrl: "selectData-01.php",
                                                        ajaxPars: "",
                                                        onchange: function () {
                                                            // inline editor 에 선언한 onchange함수는 AXGrid내부에서 사용하는 onchange 함수로 변경되어 사용할 수 없습니다.
                                                        }
                                                    },
                                                    beforeUpdate: function (val) { // 수정이 되기전 value를 처리 할 수 있음.
                                                        // 선택된 값은
                                                        console.log(val);
                                                        return val;
                                                    },
                                                    afterUpdate: function (val) { // 수정이 처리된 후
                                                        // 수정이 된 후 액션.
                                                        console.log(this);
                                                    },
 
                                                    /*
                                                     type: "text",
                                                     //textType: "password",
                                                     //maxLength: 5,
                                                     */
                                                    updateWith: ["_CUD"]
 
                                                }
                                            },
                                            {
                                                key: "combobox", label: "combobox", width: "100",
                                                editor: {
                                                    type: "select",
                                                    optionValue: "CD",
                                                    optionText: "NM",
                                                    options: [
                                                        {CD: 1, NM: "김기영"},
                                                        {CD: 2, NM: "장기영"},
                                                        {CD: 3, NM: "장서우"}
                                                    ],
                                                    beforeUpdate: function (val) { // 수정이 되기전 value를 처리 할 수 있음.
                                                        // 선택된 값은
                                                        console.log(val);
                                                        return val;
                                                    },
                                                    afterUpdate: function (val) { // 수정이 처리된 후
                                                        // 수정이 된 후 액션.
                                                        console.log(this);
                                                    }
                                                }
                                            },
                                            {
                                                key: "combobox1", label: "combobox1", width: "100",
                                                formatter: function () {
                                                    return this.value.NM;
                                                },
                                                editor: {
                                                    type: "select",
                                                    optionValue: "CD",
                                                    optionText: "NM",
                                                    options: function () {
                                                        return this.value.options;
                                                    },
                                                    beforeUpdate: function (val) { // 수정이 되기전 value를 처리 할 수 있음.
                                                        // 선택된 값은
                                                        var NM = "";
                                                        for(var oi = 0, l = this.value.options.length;oi < l;oi++){
                                                            if(this.value.options[oi].CD == val){
                                                                NM = this.value.options[oi].NM;
                                                                break;
                                                            }
                                                        }
                                                        this.value.CD = val;
                                                        this.value.NM = NM;
                                                        return this.value;
                                                    },
                                                    afterUpdate: function (val) { // 수정이 처리된 후
                                                        // 수정이 된 후 액션.
                                                        console.log(this);
                                                    }
                                                }
                                            },
                                            {
                                                key: "combobox2", label: "combobox2", width: "100",
                                                formatter: function (val) {
                                                    if (Object.isObject(this.value)) {
                                                        return this.value.NM;
                                                    }
                                                    else {
                                                        return this.value;
                                                    }
                                                },
                                                editor: {
                                                    type: "AXSelect",
                                                    config: {
                                                        reserveKeys: {
                                                            options: "list",
                                                            optionValue: "CD",
                                                            optionText: "NM"
                                                        }
                                                        ,
                                                        ajaxUrl: "selectData-01.php",
                                                        ajaxPars: "",
                                                        onchange: function () {
                                                            // inline editor 에 선언한 onchange함수는 AXGrid내부에서 사용하는 onchange 함수로 변경되어 사용할 수 없습니다.
                                                        }
                                                    }
                                                    ,
                                                    beforeUpdate: function (val) { // 수정이 되기전 value를 처리 할 수 있음.
                                                        // 선택된 값은
                                                        console.log(val);
                                                        return val;
                                                    }
                                                    ,
                                                    afterUpdate: function (val) { // 수정이 처리된 후
                                                        // 수정이 된 후 액션.
                                                        //console.log(this);
                                                    }
                                                }
                                            },
                                            {
                                                key: "date", label: "date", width: "105", align: "center",
                                                editor: {
                                                    type: "calendar",
                                                    config: {
                                                        separator: "-"
                                                    },
                                                    disabled: function(){
                                                        return this.item._CUD != "C";
                                                    },
                                                    updateWith: ["_CUD"]
                                                }
                                            },
                                            {
                                                key: "money", label: "money", width: "100", align: "right",
                                                formatter: "money",
                                                editor: {
                                                    type: "money",
                                                    updateWith: ["number", "_CUD"]
                                                }
                                            },
                                            {
                                                key: "number", label: "number", width: "80", align: "right",
                                                formatter: "money",
                                                editor: {
                                                    type: "number",
                                                    updateWith: ["money", "_CUD"]
                                                }
                                            },
                                            {
                                                key: "checkbox", label: "checkbox", width: "50", align: "center",
                                                editor: {
                                                    type: "checkbox",
                                                    beforeUpdate: function (val) {
                                                        return (val == true) ? "Y" : "N";
                                                    }
                                                }
                                            },
                                            {
                                                key: "radio", label: "radio", width: "50", align: "center",
                                                editor: {
                                                    type: "radio"
                                                }
                                            },
                                            {
                                                key: "finder", label: "finder", width: "100", align: "center",
                                                editor: {
                                                    type: "finder",
                                                    formatter: function () {
                                                        return (this.item.finder || "");
                                                    }
 
                                                    ,
                                                    finder: {
                                                        onclick: function () {
                                                            alert("새창 열기");
                                                        }
                                                    }
                                                }
                                            }
                                        ],
                                        colHeadAlign: "center", // 헤드의 기본 정렬 값 ( colHeadAlign 을 지정하면 colGroup 에서 정의한 정렬이 무시되고 colHeadAlign : false 이거나 없으면 colGroup 에서 정의한 속성이 적용됩니다.
                                        body: {
                                            onclick: function () {
                                                //trace(this.index);
                                            }
                                        }
                                        ,
                                        page: {
                                            paging: false
                                        }
                                    }
                            );
 
                            var list = [
                                {
                                    no: 1,
                                    string: "AXGrid 첫번째 줄 입니다.",
                                    combobox: 1,
                                    combobox1: {CD: '1', NM: '김기영', options: [{CD: 1, NM: "김기영"}, {CD: 2, NM: "장기영"}, {CD: 3, NM: "장서우"}]},
                                    combobox2: {CD: 1, NM: "김기영"},
                                    date: "2013-01-18",
                                    money: 1709401,
                                    number: 10,
                                    checkbox: 1,
                                    radio: 1,
                                    finder: "선택"
                                },
                                {
                                    no: 2,
                                    string: "AXGrid 첫번째 줄 입니다.",
                                    combobox: 2,
                                    combobox1: {CD: '1', NM: '황인서', options: [{CD: 1, NM: "황인서"}, {CD: 2, NM: "장인서"}, {CD: 3, NM: "김인서"}]},
                                    combobox2: {CD: 1, NM: "김기영"},
                                    date: "2013-01-18",
                                    money: 1709401,
                                    number: 10,
                                    checkbox: 1,
                                    radio: 1,
                                    finder: "선택"
                                },
                                {
                                    no: 3,
                                    string: "AXGrid 첫번째 줄 입니다.",
                                    combobox: 3,
                                    combobox1: {CD: '1', NM: '김동근', options: [{CD: 1, NM: "김동근"}, {CD: 2, NM: "박동근"}, {CD: 3, NM: "장동근"}]},
                                    combobox2: {CD: 1, NM: "김기영"},
                                    date: "2013-01-18",
                                    money: 1709401,
                                    number: 10,
                                    checkbox: 1,
                                    radio: 1,
                                    finder: "선택"
                                }
                            ];
                            myGrid.setList(list);
                            //trace(myGrid.getSortParam());
 
                        },
                        getExcel: function (type) {
                            var obj = myGrid.getExcelFormat(type, function () {
                                return this.key != "no" && this.key != "finder";
                            });
                            $("#printout").html(obj);
                        }
                        ,
                        getSelectedItem: function () {
                            trace(this.target.getSelectedItem());
                            toast.push('콘솔창에 데이터를 출력하였습니다.');
                        }
                        ,
                        append: function () {
                            this.target.pushList(
                                    {
                                        no: this.target.list.length,
                                        string: "",
                                        combobox: {optionValue: 1, optionText: "김기영"},
                                        combobox1: {CD: '1', NM: '김동근', options: [{CD: 1, NM: "김동근"}, {CD: 2, NM: "박동근"}, {CD: 3, NM: "장동근"}]},
                                        combobox2: {CD: 1, NM: "김기영"},
                                        date: "2013-01-18",
                                        money: 1709401,
                                        number: 10,
                                        checkbox: 1,
                                        radio: 1,
                                        finder: "선택"
                                    }
                            );
                            this.target.setFocus(this.target.list.length - 1);
                        }
                        ,
                        remove: function () {
                            var checkedList = myGrid.getCheckedListWithIndex(0);// colSeq
                            if (checkedList.length == 0) {
                                alert("선택된 목록이 없습니다. 삭제하시려는 목록을 체크하세요");
                                return;
                            }
                            this.target.removeListIndex(checkedList);
                            // 전달한 개체와 비교하여 일치하는 대상을 제거 합니다. 이때 고유한 값이 아닌 항목을 전달 할 때에는 에러가 발생 할 수 있습니다.
                        }
                    }
                };
 
        jQuery(document.body).ready(function () {
            fnObj.pageStart();
        });

</script>	
</head>
<body>
    <h1>AXGrid RWD</h1>
    <div id="AXGridTarget"></div>
</body>
</html>