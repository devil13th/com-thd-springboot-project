(this["webpackJsonpcom-thd-springboot-project-knowledge-client"]=this["webpackJsonpcom-thd-springboot-project-knowledge-client"]||[]).push([[177],{371:function(t,e){!function(t){t.languages.http={"request-line":{pattern:/^(?:POST|GET|PUT|DELETE|OPTIONS|PATCH|TRACE|CONNECT)\s(?:https?:\/\/|\/)\S+\sHTTP\/[0-9.]+/m,inside:{property:/^(?:POST|GET|PUT|DELETE|OPTIONS|PATCH|TRACE|CONNECT)\b/,"attr-name":/:\w+/}},"response-status":{pattern:/^HTTP\/1.[01] \d.*/m,inside:{property:{pattern:/(^HTTP\/1.[01] )\d.*/i,lookbehind:!0}}},"header-name":{pattern:/^[\w-]+:(?=.)/m,alias:"keyword"}};var e,a,n,p=t.languages,i={"application/javascript":p.javascript,"application/json":p.json||p.javascript,"application/xml":p.xml,"text/xml":p.xml,"text/html":p.html,"text/css":p.css},s={"application/json":!0,"application/xml":!0};for(var o in i)if(i[o]){e=e||{};var r=s[o]?(n=(a=o).replace(/^[a-z]+\//,""),"(?:"+a+"|\\w+/(?:[\\w.-]+\\+)+"+n+"(?![+\\w.-]))"):o;e[o.replace(/\//g,"-")]={pattern:RegExp("(content-type:\\s*"+r+".*)(?:\\r?\\n|\\r){2}[\\s\\S]*","i"),lookbehind:!0,inside:i[o]}}e&&t.languages.insertBefore("http","header-name",e)}(Prism)}}]);
//# sourceMappingURL=177.aa7ccf75.chunk.js.map