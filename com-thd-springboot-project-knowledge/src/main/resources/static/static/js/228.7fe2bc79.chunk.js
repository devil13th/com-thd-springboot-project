(this["webpackJsonpcom-thd-springboot-project-knowledge-client"]=this["webpackJsonpcom-thd-springboot-project-knowledge-client"]||[]).push([[228],{422:function(e,n){!function(e){e.languages.kotlin=e.languages.extend("clike",{keyword:{pattern:/(^|[^.])\b(?:abstract|actual|annotation|as|break|by|catch|class|companion|const|constructor|continue|crossinline|data|do|dynamic|else|enum|expect|external|final|finally|for|fun|get|if|import|in|infix|init|inline|inner|interface|internal|is|lateinit|noinline|null|object|open|operator|out|override|package|private|protected|public|reified|return|sealed|set|super|suspend|tailrec|this|throw|to|try|typealias|val|var|vararg|when|where|while)\b/,lookbehind:!0},function:[{pattern:/(?:`[^\r\n`]+`|\w+)(?=\s*\()/,greedy:!0},{pattern:/(\.)(?:`[^\r\n`]+`|\w+)(?=\s*\{)/,lookbehind:!0,greedy:!0}],number:/\b(?:0[xX][\da-fA-F]+(?:_[\da-fA-F]+)*|0[bB][01]+(?:_[01]+)*|\d+(?:_\d+)*(?:\.\d+(?:_\d+)*)?(?:[eE][+-]?\d+(?:_\d+)*)?[fFL]?)\b/,operator:/\+[+=]?|-[-=>]?|==?=?|!(?:!|==?)?|[\/*%<>]=?|[?:]:?|\.\.|&&|\|\||\b(?:and|inv|or|shl|shr|ushr|xor)\b/}),delete e.languages.kotlin["class-name"],e.languages.insertBefore("kotlin","string",{"raw-string":{pattern:/("""|''')[\s\S]*?\1/,alias:"string"}}),e.languages.insertBefore("kotlin","keyword",{annotation:{pattern:/\B@(?:\w+:)?(?:[A-Z]\w*|\[[^\]]+\])/,alias:"builtin"}}),e.languages.insertBefore("kotlin","function",{label:{pattern:/\w+@|@\w+/,alias:"symbol"}});var n=[{pattern:/\$\{[^}]+\}/,inside:{delimiter:{pattern:/^\$\{|\}$/,alias:"variable"},rest:e.languages.kotlin}},{pattern:/\$\w+/,alias:"variable"}];e.languages.kotlin.string.inside=e.languages.kotlin["raw-string"].inside={interpolation:n},e.languages.kt=e.languages.kotlin,e.languages.kts=e.languages.kotlin}(Prism)}}]);
//# sourceMappingURL=228.7fe2bc79.chunk.js.map