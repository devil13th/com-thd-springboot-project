(this["webpackJsonpcom-thd-springboot-project-knowledge-client"]=this["webpackJsonpcom-thd-springboot-project-knowledge-client"]||[]).push([[241],{435:function(e,n){!function(e){function n(e){return RegExp("(\\()"+e+"(?=[\\s\\)])")}function t(e){return RegExp("([\\s([])"+e+"(?=[\\s)])")}var a="[-+*/_~!@$%^=<>{}\\w]+",o="(\\()",s="(?=\\))",i="(?=\\s)",r={heading:{pattern:/;;;.*/,alias:["comment","title"]},comment:/;.*/,string:{pattern:/"(?:[^"\\]|\\.)*"/,greedy:!0,inside:{argument:/[-A-Z]+(?=[.,\s])/,symbol:RegExp("`"+a+"'")}},"quoted-symbol":{pattern:RegExp("#?'"+a),alias:["variable","symbol"]},"lisp-property":{pattern:RegExp(":"+a),alias:"property"},splice:{pattern:RegExp(",@?"+a),alias:["symbol","variable"]},keyword:[{pattern:RegExp(o+"(?:(?:lexical-)?let\\*?|(?:cl-)?letf|if|when|while|unless|cons|cl-loop|and|or|not|cond|setq|error|message|null|require|provide|use-package)"+i),lookbehind:!0},{pattern:RegExp(o+"(?:for|do|collect|return|finally|append|concat|in|by)"+i),lookbehind:!0}],declare:{pattern:n("declare"),lookbehind:!0,alias:"keyword"},interactive:{pattern:n("interactive"),lookbehind:!0,alias:"keyword"},boolean:{pattern:t("(?:t|nil)"),lookbehind:!0},number:{pattern:t("[-+]?\\d+(?:\\.\\d*)?"),lookbehind:!0},defvar:{pattern:RegExp(o+"def(?:var|const|custom|group)\\s+"+a),lookbehind:!0,inside:{keyword:/^def[a-z]+/,variable:RegExp(a)}},defun:{pattern:RegExp(o+"(?:cl-)?(?:defun\\*?|defmacro)\\s+"+a+"\\s+\\([\\s\\S]*?\\)"),lookbehind:!0,inside:{keyword:/^(?:cl-)?def\S+/,arguments:null,function:{pattern:RegExp("(^\\s)"+a),lookbehind:!0},punctuation:/[()]/}},lambda:{pattern:RegExp(o+"lambda\\s+\\(\\s*(?:&?"+a+"(?:\\s+&?"+a+")*\\s*)?\\)"),lookbehind:!0,inside:{keyword:/^lambda/,arguments:null,punctuation:/[()]/}},car:{pattern:RegExp(o+a),lookbehind:!0},punctuation:[/(?:['`,]?\(|[)\[\]])/,{pattern:/(\s)\.(?=\s)/,lookbehind:!0}]},l={"lisp-marker":RegExp("&[-+*/_~!@$%^=<>{}\\w]+"),rest:{argument:{pattern:RegExp(a),alias:"variable"},varform:{pattern:RegExp(o+a+"\\s+\\S[\\s\\S]*"+s),lookbehind:!0,inside:{string:r.string,boolean:r.boolean,number:r.number,symbol:r.symbol,punctuation:/[()]/}}}},p="\\S+(?:\\s+\\S+)*",d={pattern:RegExp(o+"[\\s\\S]*"+s),lookbehind:!0,inside:{"rest-vars":{pattern:RegExp("&(?:rest|body)\\s+"+p),inside:l},"other-marker-vars":{pattern:RegExp("&(?:optional|aux)\\s+"+p),inside:l},keys:{pattern:RegExp("&key\\s+"+p+"(?:\\s+&allow-other-keys)?"),inside:l},argument:{pattern:RegExp(a),alias:"variable"},punctuation:/[()]/}};r.lambda.inside.arguments=d,r.defun.inside.arguments=e.util.clone(d),r.defun.inside.arguments.inside.sublist=d,e.languages.lisp=r,e.languages.elisp=r,e.languages.emacs=r,e.languages["emacs-lisp"]=r}(Prism)}}]);
//# sourceMappingURL=241.587c3582.chunk.js.map