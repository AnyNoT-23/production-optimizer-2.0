<template>
  <div class="container">

    <h1>Dashboard</h1>

    <div class="cards">

      <div class="card">
        <h3>Top Product</h3>
        <p>{{ topProduct }}</p>
      </div>

      <div class="card">
        <h3>Total Production</h3>
        <p>{{ totalProduction }}</p>
      </div>

      <div class="card">
        <h3>Low Stock Materials</h3>
        <p>{{ lowStock }}</p>
      </div>

    </div>

  </div>
</template>

<script>
import api from "../services/api"

export default {
  data() {
    return {
      results: [],
      materials: [],
      topProduct: "-",
      totalProduction: 0,
      lowStock: 0
    }
  },

  async mounted() {
    const [prod, rm, pc] = await Promise.all([
      api.get("/products"),
      api.get("/raw-materials"),
      api.get("/product-compositions")
    ])

    const products = prod.data
    const materials = rm.data
    const compositions = pc.data

    this.materials = materials

    const results = products.map(p => {
      const comps = compositions.filter(c => c.product?.id === p.id)

      let max = Infinity

      comps.forEach(c => {
        const m = materials.find(x => x.id === c.rawMaterial?.id)
        if (!m) return

        const possible = m.stockQuantity / c.requiredQuantity
        if (possible < max) max = possible
      })

      if (max === Infinity) max = 0

      return {
        name: p.name,
        production: Math.floor(max)
      }
    })

    this.results = results

    // top product
    const top = results.sort((a,b) => b.production - a.production)[0]
    this.topProduct = top?.name || "-"

    // total
    this.totalProduction = results.reduce((sum, r) => sum + r.production, 0)

    // low stock
    this.lowStock = materials.filter(m => m.stockQuantity < 10).length
  }
}
</script>

<style scoped>
.container{
  max-width:1000px;
  margin:auto;
}

.cards{
  display:flex;
  gap:20px;
}

.card{
  flex:1;
  padding:20px;
  background:#f3f4f6;
  border-radius:10px;
  text-align:center;
}
</style>