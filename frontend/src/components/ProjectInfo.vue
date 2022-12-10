<template>
    <header>
        <div class="corner author-info">
            <p>Rakhmatullin Ramazan</p>
            <p>P32131</p>
        </div>
        <div class="project-name">
            <h1>Laboratory work #1</h1>
        </div>
        <div class="corner variant">
            <p>Variant: 1515</p>
        </div>
    </header>
</template>
<script>
export default {
    
}
</script>
<style scoped>
header {
    padding: 15px 25px;
    display: flex;
    flex-flow: row nowrap;
    justify-content: space-between;
    align-items: center;
}

.corner {
    width: 220px;
}

.project-name {
    text-align: center;
}

.variant {
    text-align: right;
}
</style>