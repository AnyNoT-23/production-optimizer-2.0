<template>
  <div class="container">
    <h1>Production Analysis</h1>
    <div class="toolbar">
      <input v-model="search" placeholder="Search product..." />
    </div>
    <table>
      <thead>
        <tr>
          <th>Product</th>
          <th>Max Production</th>
        </tr>
      </thead>

      <tbody>
        <tr v-for="p in filteredResults" :key="p.productId">
          <td>{{ p.productName }}</td>
          <td>{{ p.maxProduction }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import api from "../services/api";

export default {
  data() {
    return {
      search: "",
      products: [],
      materials: [],
      compositions: [],
      results: [],
    };
  },

  methods: {
    async loadData() {
      const [p, rm, pc] = await Promise.all([
        api.get("/products"),
        api.get("/raw-materials"),
        api.get("/product-compositions"),
      ]);

      this.products = p.data;
      this.materials = rm.data;
      this.compositions = pc.data;

      this.calculate();
    },

    calculate() {
      this.results = this.products.map((product) => {
        const comps = this.compositions.filter(
          (c) => c.product?.id === product.id,
        );

        let maxProduction = Infinity;

        comps.forEach((c) => {
          const material = this.materials.find(
            (m) => m.id === c.rawMaterial?.id,
          );

          if (!material) return;

          const possible = material.stockQuantity / c.requiredQuantity;

          if (possible < maxProduction) {
            maxProduction = possible;
          }
        });

        if (maxProduction === Infinity) {
          maxProduction = 0;
        }

        return {
          productId: product.id,
          productName: product.name,
          maxProduction: Math.floor(maxProduction),
        };
      });
    },
  },

  mounted() {
    this.loadData();
  },
  computed: {
    filteredResults() {
      return this.results.filter((r) =>
        r.productName.toLowerCase().includes(this.search.toLowerCase()),
      );
    },
  },
};
</script>

<style scoped>
.container {
  max-width: 1000px;
  margin: auto;
}

.toolbar {
  margin-bottom: 20px;
}

input {
  width: 100%;
  padding: 10px;
  border-radius: 6px;
  border: 1px solid #ccc;
}
</style>
