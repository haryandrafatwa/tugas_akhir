var matrix = [[0,0,0,0,0,0,1,0,1,0,0,3,0,0,0,0,1,3,0,0,0,0,0,0,1,2,0,0,0,0,0,2],[1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,7,7,0,0,0,0,1,2,0,0,0,0,0,0,0,8,2,0,0,0,0,2],[0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,11,0,0,0,0,0,1],[1,0,0,0,0,0,4,0,0,0,0,4,0,0,0,0,0,0,0,1,0,1,0,0,0,4,0,0,0,0,0,4],[0,0,0,0,0,0,0,0,0,0,5,5,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,3],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,14,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,2],[0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,11,0,0,0,15,0,0,0,0,0,0,14,0,0,0,0,0,0,0,0,16,0,0,0,0,16],[0,0,0,0,0,0,0,0,0,0,5,5,0,0,0,0,0,1,0,0,0,0,0,0,0,4,1,0,0,0,0,4],[0,0,0,0,0,0,0,0,0,0,6,6,0,0,0,0,0,1,0,0,0,0,0,0,0,5,1,0,0,0,0,3],[0,0,0,0,0,0,2,0,4,0,3,7,0,0,0,0,1,6,0,0,0,0,0,0,0,3,1,0,0,0,0,6],[0,0,0,1,0,0,0,0,0,0,5,5,1,0,0,0,1,3,0,0,0,0,0,0,0,7,1,0,0,0,0,5],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,1,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,1,0,0,0,0,1,0,0,0,1,0,0,0,0,2],[0,0,0,0,0,0,0,0,0,0,2,12,0,0,0,0,0,12,0,0,0,0,12,0,0,0,3,0,0,0,0,6],[0,0,0,4,0,0,0,0,0,0,0,4,0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,4],[0,0,0,1,0,0,0,2,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,14,3,0,0,0,0,0],[0,0,0,5,0,0,0,0,0,0,3,10,1,0,0,0,3,10,0,0,0,0,0,0,0,6,1,0,0,0,0,8],[0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,2,1,0,0,0,0,3],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,5,5,0,0,0,0,0,2,0,0,0,0,0,0,0,2,0,0,0,0,0,5],[0,0,0,0,0,0,0,0,1,0,1,3,0,0,0,0,0,2,0,0,0,0,0,0,0,7,0,0,0,0,0,3],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0]]
var packages = [{
"name": " org.d3ifcool.finpro.mahasiswa.fragments.parent", "color": " #3182bd"
}
,{
"name": " org.d3ifcool.finpro.adapters", "color": " #6baed6"
}
,{
"name": " org.d3ifcool.finpro.koor.activities.detail", "color": " #9ecae1"
}
,{
"name": " org.d3ifcool.finpro.dosen.adapters.recyclerview", "color": " #c6dbef"
}
,{
"name": " org.d3ifcool.finpro.activities", "color": " #e6550d"
}
,{
"name": " org.d3ifcool.finpro.koor.activities.editor.create", "color": " #fd8d3c"
}
,{
"name": " org.d3ifcool.finpro.core.interfaces.objects", "color": " #fdae6b"
}
,{
"name": " org.d3ifcool.finpro.dosen.adapters.viewpager", "color": " #fdd0a2"
}
,{
"name": " org.d3ifcool.finpro.mahasiswa.adapters.recyclerview", "color": " #31a354"
}
,{
"name": " org.d3ifcool.finpro.mahasiswa.fragments.child", "color": " #74c476"
}
,{
"name": " org.d3ifcool.finpro.core.interfaces.works", "color": " #a1d99b"
}
,{
"name": " org.d3ifcool.finpro.core.presenters", "color": " #c7e9c0"
}
,{
"name": " org.d3ifcool.finpro.dosen.activities.editor.update", "color": " #756bb1"
}
,{
"name": " org.d3ifcool.finpro.koor.activities.editor.update", "color": " #9e9ac8"
}
,{
"name": " org.d3ifcool.finpro.mahasiswa.activities", "color": " #bcbddc"
}
,{
"name": " org.d3ifcool.finpro.dosen.activities.detail", "color": " #dadaeb"
}
,{
"name": " org.d3ifcool.finpro.core.adapters", "color": " #636363"
}
,{
"name": " org.d3ifcool.finpro.core.interfaces.lists", "color": " #969696"
}
,{
"name": " org.d3ifcool.finpro.koor.activities", "color": " #bdbdbd"
}
,{
"name": " org.d3ifcool.finpro.koor.fragments", "color": " #d9d9d9"
}
,{
"name": " org.d3ifcool.finpro.dosen.fragments.child", "color": " #3182bd"
}
,{
"name": " org.d3ifcool.finpro.dosen.fragments.parent", "color": " #6baed6"
}
,{
"name": " org.d3ifcool.finpro.koor.adapters", "color": " #9ecae1"
}
,{
"name": " org.d3ifcool.finpro.dosen.activities", "color": " #c6dbef"
}
,{
"name": " org.d3ifcool.finpro.mahasiswa.adapters.viewpager", "color": " #e6550d"
}
,{
"name": " org.d3ifcool.finpro.core.models", "color": " #fd8d3c"
}
,{
"name": " org.d3ifcool.finpro.core.api", "color": " #fdae6b"
}
,{
"name": " org.d3ifcool.finpro.mahasiswa.activities.editor", "color": " #fdd0a2"
}
,{
"name": " org.d3ifcool.finpro", "color": " #31a354"
}
,{
"name": " org.d3ifcool.finpro.dosen.activities.editor.create", "color": " #74c476"
}
,{
"name": " org.d3ifcool.finpro.mahasiswa.activities.detail", "color": " #a1d99b"
}
,{
"name": " org.d3ifcool.finpro.core.helpers", "color": " #c7e9c0"
}
];
